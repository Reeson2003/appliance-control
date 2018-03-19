package ru.reeson2003.applianceConntrol.service.api.entity;

import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Appliance;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public class ApplianceEntityImpl implements ApplianceEntity {
    private Long id;
    private String identifier;
    private State state;
    private Collection<Action> actions;

    public ApplianceEntityImpl(Long id, Appliance appliance) {
        this.id = id;
        this.identifier = appliance.getIdentifier();
        this.state = appliance.getState();
        this.actions = appliance.getActions();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Collection<Action> getActions() {
        return actions;
    }
}
