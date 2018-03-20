package ru.reeson2003.applianceControl.service.inMemory;

import ru.reeson2003.applianceControl.service.api.ApplianceList;
import ru.reeson2003.applianceControl.service.api.ApplianceService;
import ru.reeson2003.applianceControl.service.api.IdentifiedAppliance;
import ru.reeson2003.applianceControl.service.api.IdentifiedApplianceImpl;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;
import ru.reeson2003.applianceControl.api.PerformActionException;
import ru.reeson2003.applianceControl.api.StateChangeListener;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class InMemoryApplianceService implements ApplianceService {
    private AtomicLong idGenerator = new AtomicLong(0);
    private Map<Long, Appliance> appliances = new ConcurrentHashMap<>();
    private ApplianceList applianceList;
    private StateChangeListener listener = new SimpleApplianceListener();

    public InMemoryApplianceService(ApplianceList applianceList) {
        this.applianceList = applianceList;
    }

    @Override
    public Collection<IdentifiedAppliance> getAppliances() {
        return appliances.entrySet().stream()
                .map(entry -> new IdentifiedApplianceImpl(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IdentifiedAppliance> getAppliancesByName(String name) {
        return appliances.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getIdentifier().equals(name))
                .map(entry -> new IdentifiedApplianceImpl(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public IdentifiedAppliance getApplianceById(Long id) {
        if (!appliances.containsKey(id))
            return null;
        return new IdentifiedApplianceImpl(appliances.get(id), id);
    }

    @Override
    public IdentifiedAppliance addAppliance(String applianceName) {
        try {
            return CompletableFuture
                    .supplyAsync(() -> idGenerator.getAndIncrement())
                    .thenApply(id -> {
                        Appliance appliance = applianceList.newAppliance(applianceName);
                        appliance.subscribeStateChange(listener);
                        appliances.put(id, appliance);
                        return new IdentifiedApplianceImpl(appliance, id);
                    })
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAppliance(Long applianceId) {
        appliances.remove(applianceId).unsubscribeStateChange(listener);
    }

    @Override
    public void performAction(Long applianceId, Action action) {
        try {
            appliances.get(applianceId).performAction(action);
        } catch (PerformActionException e) {
            throw new RuntimeException(e);
        }
    }
}
