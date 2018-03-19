package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pavel Gavrilov
 */
public class Lamp extends AbstractAppliance implements ActionNames, StateNames {
    private static final String IDENTIFIER = "Lamp";
    private State state;
    private List<Action> actions;

    public Lamp() {
        super(IDENTIFIER);
        setOffState();
    }

    private Lamp(State state) {
        super(IDENTIFIER);
        switch (state.getName()) {
            case ON:
                setOnState();
                return;
            case OFF:
                setOffState();
                return;
            default: throw new IllegalArgumentException("Incorrect state: " + state.getName());
        }
    }

    @Override
    public Appliance getNew() {
        return new Lamp();
    }

    @Override
    public Appliance getOld(State state) {
        return new Lamp(state);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Collection<Action> getActions() {
        return actions;
    }

    @Override
    public void performAction(Action action) throws PerformActionException {
        switch (action.getName()) {
            case TURN_ON:
                performTurnOnAction();
                break;
            case TURN_OFF:
                performTurnOffAction();
                break;
            default:
                throw new PerformActionException("Incompatible action");
        }
    }

    private void performTurnOnAction() {
        CompletableFuture
                .supplyAsync(this::setOnState)
                .thenAccept(this::notifyListeners);
    }

    private State setOnState() {
        this.state = new LampOnState();
        this.actions = Collections.singletonList(new LampTurnOffAction());
        return this.state;
    }

    private void performTurnOffAction() {
        CompletableFuture
                .supplyAsync(this::setOffState)
                .thenAccept(this::notifyListeners);
    }

    private State setOffState() {
        this.state = new LampOffState();
        this.actions = Collections.singletonList(new LampTurnOnAction());
        return this.state;
    }
}
