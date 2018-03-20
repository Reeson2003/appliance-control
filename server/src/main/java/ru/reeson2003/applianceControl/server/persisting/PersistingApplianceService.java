package ru.reeson2003.applianceControl.server.persisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedAppliance;
import ru.reeson2003.applianceConntrol.service.api.IdentifiedApplianceImpl;
import ru.reeson2003.applianceConntrol.service.api.entity.ApplianceEntity;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.server.persisting.entity.PersistingAppliance;
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
                .map(persistingAppliance ->
                        new IdentifiedApplianceImpl(persistingAppliance.getId(),))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IdentifiedAppliance> getAppliancesByName(String name) {
        return repository.findByName()
                .stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    @Override
    public IdentifiedAppliance getApplianceById(Long id) {
        return convertTo(repository.getOne(id));
    }

    @Override
    public IdentifiedAppliance addAppliance(String applianceName) {
        repository.save(convertFrom(applianceList.newAppliance(applianceName)))
        return null;
    }

    @Override
    public void removeAppliance(Long applianceId) {

    }

    @Override
    public void performAction(Long applianceId, Action action) {

    }
}
