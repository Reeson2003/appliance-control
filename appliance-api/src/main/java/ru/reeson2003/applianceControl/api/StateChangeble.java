package ru.reeson2003.applianceControl.api;

/**
 * @author Pavel Gavrilov
 */
public interface StateChangeble {
    void subscribeStateChange(StateChangeListener listener);

    void unsubscribeStateChange(StateChangeListener listener);
}
