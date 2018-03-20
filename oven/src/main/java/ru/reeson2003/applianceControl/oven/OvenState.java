package ru.reeson2003.applianceControl.oven;

import ru.reeson2003.applianceControl.api.Parameter;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public abstract class OvenState extends State implements StateNames {

    public OvenState(String name, Collection<Parameter> parameters) {
        super(name, parameters);
    }
}
