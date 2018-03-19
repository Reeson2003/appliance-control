package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.State;

import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public abstract class LampState extends State implements StateNames {

    public LampState(String name) {
        super(name, Collections.emptyList());
    }
}
