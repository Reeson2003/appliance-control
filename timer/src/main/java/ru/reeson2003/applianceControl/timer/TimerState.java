package ru.reeson2003.applianceControl.timer;

import ru.reeson2003.applianceControl.api.Parameter;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public abstract class TimerState extends State implements StateNames, ParameterNames {
    public TimerState(String name, Collection<Parameter> parameters) {
        super(name, parameters);
    }
}
