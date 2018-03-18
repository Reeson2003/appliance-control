package ru.reeson2003.applianceConntrol.service.api;

import ru.reeson2003.applianceControl.api.Appliance;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceList {
    void registerAppliance(Appliance appliance);

    void removeAppliance(String applianceName);

    Collection<String> getApplianceList();

    Appliance newAppliance(String name);
}
