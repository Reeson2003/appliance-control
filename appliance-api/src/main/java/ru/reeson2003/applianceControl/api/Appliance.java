package ru.reeson2003.applianceControl.api;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface Appliance extends Prototype, StateChangeble {
    String getIdentifier();

    State getState();

    Collection<Action> getActions();

    void performAction(Action action) throws PerformActionException;
}
