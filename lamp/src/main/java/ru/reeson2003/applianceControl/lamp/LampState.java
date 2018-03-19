package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.Parameter;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public abstract class LampState implements State, StateNames {
    @Override
    public Collection<Parameter> getParameters() {
        return Collections.emptyList();
    }
}
