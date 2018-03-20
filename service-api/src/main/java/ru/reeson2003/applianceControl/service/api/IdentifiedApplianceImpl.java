package ru.reeson2003.applianceControl.service.api;

import ru.reeson2003.applianceControl.api.*;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public class IdentifiedApplianceImpl implements IdentifiedAppliance {
    private Appliance origin;
    private Long id;

    public IdentifiedApplianceImpl(Appliance origin, Long id) {
        this.origin = origin;
        this.id = id;
    }

    public IdentifiedApplianceImpl(Appliance origin) {
        this.origin = origin;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getIdentifier() {
        return origin.getIdentifier();
    }

    @Override
    public State getState() {
        return origin.getState();
    }

    @Override
    public Collection<Action> getActions() {
        return origin.getActions();
    }

    @Override
    public void performAction(Action action) throws PerformActionException {
        origin.performAction(action);
    }

    @Override
    public Appliance getNew() {
        return origin.getNew();
    }

    @Override
    public Appliance getOld(State state) {
        return origin.getOld(state);
    }

    @Override
    public void subscribeStateChange(StateChangeListener listener) {
        origin.subscribeStateChange(listener);
    }

    @Override
    public void unsubscribeStateChange(StateChangeListener listener) {
        origin.unsubscribeStateChange(listener);
    }
}
