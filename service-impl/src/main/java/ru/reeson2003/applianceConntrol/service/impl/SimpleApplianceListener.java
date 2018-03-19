package ru.reeson2003.applianceConntrol.service.impl;

import ru.reeson2003.applianceControl.api.State;
import ru.reeson2003.applianceControl.api.StateChangeListener;

/**
 * @author Pavel Gavrilov
 */
public class SimpleApplianceListener implements StateChangeListener {
    @Override
    public void onStateChange(State state) {
        System.out.println(state.getName());
    }
}
