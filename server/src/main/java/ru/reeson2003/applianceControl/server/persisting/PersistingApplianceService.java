package ru.reeson2003.applianceControl.server.persisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedAppliance;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedApplianceImpl;
import ru.reeson2003.applianceControl.api.*;
import ru.reeson2003.applianceControl.server.persisting.entity.ParameterEntity;
import ru.reeson2003.applianceControl.server.persisting.repository.ApplianceRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class PersistingApplianceService implements ApplianceService {
    private final ApplianceRepository repository;
    private final ApplianceList applianceList;
    private Map<Long, Appliance> appliances = new ConcurrentHashMap<>();
    private Map<Long, List<Listener>> listeners = new ConcurrentHashMap<>();

    public PersistingApplianceService(ApplianceRepository repository, ApplianceList applianceList) {
        this.repository = repository;
        this.applianceList = applianceList;
        init();
    }

    void init() {
        CompletableFuture.supplyAsync(repository::findAll)
                .thenAccept(applianceEntities -> {
                    applianceEntities
                            .forEach(entity ->
                                    appliances.put(entity.getId(),
                                            applianceList.oldAppliance(entity.getIdentifier(),
                                                    new State(entity.getStateName(), entity.getParameters()
                                                            .stream()
                                                            .map(p -> new Parameter(p.getKey(), p.getValue()))
                                                            .collect(Collectors.toList())))));
                });
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
                    .supplyAsync(() -> applianceList.newAppliance(applianceName))
                    .thenApply(appliance -> {
                        Long id = repository.saveAppliance(appliance);
                        Listener listener = new Listener(id);
                        appliance.subscribeStateChange(new Listener(id));
                        listeners.put(id, Arrays.asList(listener));
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
        CompletableFuture.runAsync(() -> repository.deleteById(applianceId))
                .thenApply(v -> appliances.remove(applianceId))
                .thenAccept(appliance -> listeners.get(applianceId)
                        .forEach(appliance::unsubscribeStateChange));
    }

    @Override
    public void performAction(Long applianceId, Action action) {
        try {
            appliances.get(applianceId).performAction(action);
        } catch (PerformActionException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeState(Long id, State state) {
        CompletableFuture.supplyAsync(() -> repository.getOne(id))
                .thenApply(entity -> {
                    entity.setStateName(state.getName());
                    entity.setParameters(state.getParameters().stream()
                            .map(p -> new ParameterEntity(p.getName(), p.getValue()))
                            .collect(Collectors.toList()));
                    return entity;
                })
                .thenAccept(repository::save);
    }

    private class Listener implements StateChangeListener {
        private Long id;

        public Listener(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        @Override
        public void onStateChange(State state) {
            System.out.println("Changing state of appliance id: " + id);
            changeState(id, state);
            System.out.println("New state is: " + state.getName());
        }
    }
}
