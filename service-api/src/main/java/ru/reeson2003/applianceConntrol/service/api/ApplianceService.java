package ru.reeson2003.applianceConntrol.service.api;

import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceService {
    Collection<Appliance> getAppliances();

    Collection<Appliance> getAppliancesByName();

    Appliance getApplianceById(Long id);

    Long addAppliance(Appliance appliance);

    void removeAppliance(Long applianceId);

    void performAction(Long applianceId, Action action);
}
