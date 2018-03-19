package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pavel Gavrilov
 */
public class Lamp extends AbstractAppliance implements ActionNames {
    private static final String IDENTIFIER = "Lamp";
    private State state;
    private List<Action> actions;

    public Lamp() {
        super(IDENTIFIER);
    }

    private void initState() {
        setOffState();
    }

    @Override
    public Appliance clone() {
        return new Lamp();
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
        if (!(action instanceof LampAction))
            throw new PerformActionException("Incompatible action");
        switch (action.getActionName()) {
            case TURN_ON:
                performTurnOnAction();
                break;
            case TURN_OFF:
                performTurnOffAction();
                break;
            default:
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
