package ru.reeson2003.applianceControl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Gavrilov
 */
public abstract class AbstractAppliance implements Appliance {
    private List<StateChangeListener> listeners = new ArrayList<>();
    private String identifier;

    public AbstractAppliance(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    protected void notifyListeners(State state) {
        listeners.forEach(listener -> listener.onStateChange(state));
    }

    @Override
    public void subscribeStateChange(StateChangeListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void unsubscribeStateChange(StateChangeListener listener) {
        this.listeners.remove(listener);
    }
}
