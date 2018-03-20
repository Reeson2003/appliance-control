package ru.reeson2003.applianceControl.server.persisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedAppliance;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedApplianceImpl;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;
import ru.reeson2003.applianceControl.api.Parameter;
import ru.reeson2003.applianceControl.api.State;
import ru.reeson2003.applianceControl.server.persisting.entity.ApplianceEntity;
import ru.reeson2003.applianceControl.server.persisting.entity.ParameterEntity;
import ru.reeson2003.applianceControl.server.persisting.entity.StateEntity;
import ru.reeson2003.applianceControl.server.persisting.repository.ApplianceRepository;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
@Service
public class PersistingApplianceService implements ApplianceService {
    private final ApplianceRepository repository;
    private final ApplianceList applianceList;

    @Autowired
    public PersistingApplianceService(ApplianceRepository repository, ApplianceList applianceList) {
        this.repository = repository;
        this.applianceList = applianceList;
    }

    @Override
    public Collection<IdentifiedAppliance> getAppliances() {
        return repository.findAll()
                .stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IdentifiedAppliance> getAppliancesByName(String name) {
        return repository.findByName()
                .stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public IdentifiedAppliance getApplianceById(Long id) {
        return convertFromEntity(repository.getOne(id));
    }

    @Override
    public IdentifiedAppliance addAppliance(String applianceName) {
        ApplianceEntity entity = repository.save(convertToEntity(applianceList.newAppliance(applianceName)));
        return convertFromEntity(entity);
    }

    @Override
    public void removeAppliance(Long applianceId) {
        repository.deleteById(applianceId);
    }

    @Override
    public void performAction(Long applianceId, Action action) {

    }

    private IdentifiedAppliance convertFromEntity(ApplianceEntity entity) {
        return new IdentifiedApplianceImpl(applianceList.oldAppliance(entity.getName(),
                new State(entity.getState().getName(),
                        entity.getState().getParameters()
                                .stream()
                                .map(parameterEntity -> new Parameter(parameterEntity.getKey(), parameterEntity.getValue()))
                                .collect(Collectors.toList()))),
                entity.getId());
    }

    private ApplianceEntity convertToEntity(IdentifiedAppliance identified) {
        return new ApplianceEntity(identified.getIdentifier(), convertState(identified.getState()));
    }

    private ApplianceEntity convertToEntity(Appliance appliance) {
        return new ApplianceEntity(appliance.getIdentifier(), convertState(appliance.getState()));
    }

    private StateEntity convertState(State state) {
        return new StateEntity(state.getName(), state.getParameters().stream()
        .map(parameter -> new ParameterEntity(parameter.getName(), parameter.getValue()))
        .collect(Collectors.toList()));
    }
}
