package ru.reeson2003.applianceControl.timer;

import ru.reeson2003.applianceControl.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pavel Gavrilov
 */
public class Timer extends AbstractAppliance implements ActionNames, StateNames, ParameterNames {
    private static final String IDENTIFIER = "Timer";
    private State state;
    private List<Action> actions;

    public Timer() {
        super(IDENTIFIER);
        setOffState();
    }

    private Timer(State state) {
        this();
    }

    @Override
    public Appliance getNew() {
        return new Timer();
    }

    @Override
    public Appliance getOld(State state) {
        return new Timer(state);
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
                performTurnOnAction(action);
                break;
            default:
                throw new PerformActionException("Incompatible action");
        }
    }

    private void performTurnOnAction(Action action) throws PerformActionException {
        if (action.getParameter(TIME) == null)
            throw new PerformActionException("No parameter: " + TIME);
        else {
            Integer time = getTime(action.getParameter(TIME).getValue());
            CompletableFuture
                    .supplyAsync(() -> setOnState(time))
                    .thenApply(state -> {
                        new java.util.Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                performTurnOffAction();
                            }
                        }, time * 1000);
                        return state;
                    })
                    .thenAccept(this::notifyListeners);
        }
    }

    private void performTurnOffAction() {
        CompletableFuture
                .supplyAsync(this::setOffState)
                .thenAccept(this::notifyListeners);
    }

    private int getTime(String value) throws PerformActionException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new PerformActionException("Incorrect parameter value: " + value);
        }
    }

    private State setOnState(Integer time) {
        this.state = new TimerOnState(time);
        this.actions = Collections.emptyList();
        return this.state;
    }

    private State setOffState() {
        this.state = new TimerOffState();
        this.actions = Collections.singletonList(new TimerTurnOnAction());
        return this.state;
    }
}
