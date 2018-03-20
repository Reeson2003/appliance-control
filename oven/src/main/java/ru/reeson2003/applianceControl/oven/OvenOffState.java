package ru.reeson2003.applianceControl.oven;

import ru.reeson2003.applianceControl.api.Parameter;

import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public class OvenOffState extends OvenState implements ParameterNames {

    public OvenOffState() {
        super(OFF, Collections.singletonList(new Parameter(TEMPERATURE, "0")));
    }
}
