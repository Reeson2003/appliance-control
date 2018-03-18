package ru.reeson2003.applianceConntrol.service.impl;

import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceControl.api.Appliance;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class ApplianceListImpl implements ApplianceList {
    private List<Appliance> applianceList;

    public ApplianceListImpl(Appliance... appliance) {
        this.applianceList = Arrays.asList(appliance);
    }

    @Override
    public void registerAppliance(Appliance appliance) {
        applianceList.add(appliance);
    }

    @Override
    public void removeAppliance(String applianceName) {
        applianceList = applianceList.stream()
                .filter(a -> !a.getIdentifier().equals(applianceName))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<String> getApplianceList() {
        return applianceList.stream()
                .map(Appliance::getIdentifier)
                .collect(Collectors.toList());
    }

    @Override
    public Appliance newAppliance(String name) {
        return applianceList.stream()
                .filter(appliance -> appliance.getIdentifier().equals(name))
                .map(Appliance::clone)
                .collect(Collectors.toList())
                .get(0);
    }
}
