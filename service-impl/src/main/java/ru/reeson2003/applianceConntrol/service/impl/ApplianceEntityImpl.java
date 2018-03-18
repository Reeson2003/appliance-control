package ru.reeson2003.applianceConntrol.service.impl;

import ru.reeson2003.applianceConntrol.service.api.ApplianceEntity;
import ru.reeson2003.applianceControl.api.Appliance;

/**
 * @author Pavel Gavrilov
 */
public class ApplianceEntityImpl implements ApplianceEntity {
    private Long id;
    private Appliance appliance;

    public ApplianceEntityImpl(Long id, Appliance appliance) {
        this.id = id;
        this.appliance = appliance;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Appliance getAppliance() {
        return appliance;
    }
}
