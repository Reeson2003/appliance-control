package ru.reeson2003.applianceControl.oven;

import ru.reeson2003.applianceControl.api.Parameter;

import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public class OvenOnState extends OvenState implements ParameterNames {
    public OvenOnState(Integer parameter) {
        super(ON, Collections.singletonList(new Parameter(TEMPERATURE, parameter.toString())));
    }
}
