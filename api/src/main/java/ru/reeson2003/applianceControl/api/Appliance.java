package ru.reeson2003.applianceControl.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface Appliance extends Serializable {
    String getIdentifier();

    State getState();

    Collection<Action> getActions();

    void performAction(Action action) throws PerformActionException;

    void subscribeStateChange(StateChangeListener listener);

    void unsubscribeStateChange(StateChangeListener listener);
}
