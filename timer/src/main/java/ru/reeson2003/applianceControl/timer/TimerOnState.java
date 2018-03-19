package ru.reeson2003.applianceControl.timer;

import ru.reeson2003.applianceControl.api.Parameter;

import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public class TimerOnState extends TimerState {
    public TimerOnState(Integer time) {
        super(ON, Collections.singletonList(new Parameter(TIME, time.toString())));
    }
}
