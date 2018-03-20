package ru.reeson2003.applianceControl.oven;

import ru.reeson2003.applianceControl.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class Oven extends AbstractAppliance implements ActionNames, StateNames {
    private static final String IDENTIFIER = "Oven";
    private State state;
    private List<Action> actions;

    public Oven() {
        super(IDENTIFIER);
        setOffState();
    }

    private Oven(State state) {
        super(IDENTIFIER);
        switch (state.getName()) {
            case ON:
                setOnState(getTemperature(state.getParameters()));
                return;
            case OFF:
                setOffState();
                return;
            default: throw new IllegalArgumentException("Incorrect state: " + state.getName());
        }
    }

    @Override
    public Appliance getNew() {
        return new Oven();
    }

    @Override
    public Appliance getOld(State state) {
        return new Oven(state);
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
                performTurnOnAction(action.getParameters());
                break;
            case TURN_OFF:
                performTurnOffAction();
                break;
            default:
                throw new PerformActionException("Incompatible action");
        }
    }

    private void performTurnOnAction(Collection<Parameter> parameters) {
        int temperature = getTemperature(parameters);
        turnOn(temperature);
    }

    private int getTemperature(Collection<Parameter> parameters) {
        Parameter parameter = new ArrayList<>(parameters).get(0);
        return Integer.parseInt(parameter.getValue());
    }

    private void turnOn(Integer temperature) {
        CompletableFuture.runAsync(() -> {
            int step = getTemperature(state.getParameters());
            if (temperature < step) {
                notifyListeners(setOnState(temperature));
            }
            while (temperature >= step) {
                notifyListeners(setOnState(step));
                step+=10;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private State setOnState(Integer temperature) {
        this.state = new OvenOnState(temperature);
        this.actions = Collections.singletonList(new OvenTurnOffAction());
        return this.state;
    }

    private void performTurnOffAction() {
        CompletableFuture
                .supplyAsync(this::setOffState)
                .thenAccept(this::notifyListeners);
    }

    private State setOffState() {
        this.state = new OvenOffState();
        this.actions = Collections.singletonList(new OvenTurnOnAction());
        return this.state;
    }
}
