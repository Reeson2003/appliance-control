package ru.reeson2003.applianceControl.server.persisting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reeson2003.applianceControl.api.Appliance;
import ru.reeson2003.applianceControl.server.persisting.entity.ApplianceEntity;
import ru.reeson2003.applianceControl.server.persisting.entity.ParameterEntity;

import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
@Repository
public interface ApplianceRepository extends JpaRepository<ApplianceEntity, Long> {
    default Long saveAppliance(Appliance appliance) {
        return save(new ApplianceEntity(appliance.getIdentifier(),
                appliance.getState().getName(),
                appliance.getState().getParameters().stream()
                        .map(parameter -> new ParameterEntity(parameter.getName(), parameter.getValue()))
                        .collect(Collectors.toList()))).getId();
    }
}
