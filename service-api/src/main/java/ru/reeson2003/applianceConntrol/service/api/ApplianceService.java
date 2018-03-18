package ru.reeson2003.applianceConntrol.service.api;

import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceService {
    Collection<ApplianceEntity> getAppliances();

    Collection<ApplianceEntity> getAppliancesByName(String name);

    ApplianceEntity getApplianceById(Long id);

    ApplianceEntity addAppliance(Appliance appliance);

    void removeAppliance(Long applianceId);

    void performAction(Long applianceId, Action action);
}
