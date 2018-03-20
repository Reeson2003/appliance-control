package ru.reeson2003.applianceControl.server.persisting;

import org.springframework.beans.factory.annotation.Autowired;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceConntrol.service.api.entity.ApplianceEntity;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.server.persisting.entity.PersistingAppliance;
import ru.reeson2003.applianceControl.server.persisting.repository.ApplianceRepository;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class PersistingApplianceService implements ApplianceService {
    @Autowired
    private ApplianceRepository repository;
    @Autowired
    private ApplianceList applianceList;
    @Override
    public Collection<ApplianceEntity> getAppliances() {
        return repository.findAll()
                .stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ApplianceEntity> getAppliancesByName(String name) {
        return repository.findByName()
                .stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    @Override
    public ApplianceEntity getApplianceById(Long id) {
        return convertTo(repository.getOne(id));
    }

    @Override
    public ApplianceEntity addAppliance(String applianceName) {
        repository.save(convertFrom(applianceList.newAppliance(applianceName)))
        return null;
    }

    @Override
    public void removeAppliance(Long applianceId) {

    }

    @Override
    public void performAction(Long applianceId, Action action) {

    }

    private ApplianceEntity convertTo(PersistingAppliance entity) {}

    private PersistingAppliance convertFrom(ApplianceEntity entity) {}
}
