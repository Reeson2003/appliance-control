package ru.reeson2003.applianceControl.service.api;

import ru.reeson2003.applianceControl.api.Appliance;

/**
 * @author Pavel Gavrilov
 */
public interface IdentifiedAppliance extends Appliance {
    Long getId();
}
