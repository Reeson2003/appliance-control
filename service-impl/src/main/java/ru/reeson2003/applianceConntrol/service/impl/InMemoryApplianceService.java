package ru.reeson2003.applianceConntrol.service.impl;

import ru.reeson2003.applianceConntrol.service.api.ApplianceEntity;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;
import ru.reeson2003.applianceControl.api.PerformActionException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class InMemoryApplianceService implements ApplianceService {
    private AtomicLong idGenerator = new AtomicLong(0);
    private Map<Long, Appliance> appliances = new ConcurrentHashMap<>();

    @Override
    public Collection<ApplianceEntity> getAppliances() {
        return appliances.entrySet().stream()
                .map(entry -> new ApplianceEntityImpl(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ApplianceEntity> getAppliancesByName(String name) {
        return appliances.entrySet().stream()
                .filter(entry -> entry.getValue().getIdentifier().equals(name))
                .map(entry -> new ApplianceEntityImpl(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public ApplianceEntity getApplianceById(Long id) {
        if (!appliances.containsKey(id))
            return null;
        return new ApplianceEntityImpl(id, appliances.get(id));
    }

    @Override
    public ApplianceEntity addAppliance(Appliance appliance) {
        Long id = idGenerator.getAndIncrement();
        appliances.put(id, appliance);
        return new ApplianceEntityImpl(id, appliance);
    }

    @Override
    public void removeAppliance(Long applianceId) {
        appliances.remove(applianceId);
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
