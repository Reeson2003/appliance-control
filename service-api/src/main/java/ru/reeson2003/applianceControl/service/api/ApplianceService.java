package ru.reeson2003.applianceControl.service.api;

import ru.reeson2003.applianceControl.api.Action;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceService {
    Collection<IdentifiedAppliance> getAppliances();

    Collection<IdentifiedAppliance> getAppliancesByName(String name);

    IdentifiedAppliance getApplianceById(Long id);

    IdentifiedAppliance addAppliance(String applianceName);

    void removeAppliance(Long applianceId);

    void performAction(Long applianceId, Action action);
}
