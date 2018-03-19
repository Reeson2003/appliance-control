package ru.reeson2003.applianceControl.timer;

import ru.reeson2003.applianceControl.api.Action;

/**
 * @author Pavel Gavrilov
 */
public abstract class TimerAction extends Action implements ActionNames, ParameterNames {
    public TimerAction(String name) {
        super(name);
    }
}
