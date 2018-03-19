package ru.reeson2003.applianceControl.api;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public class Action {
    private String name;
    private Collection<Parameter> parameters;

    public Action(String name) {
        this.name = name;
        this.parameters = new ArrayList<>();
    }

    public Collection<Parameter> getParameters() {
        return parameters;
    }

    public void setParameter(String name, String value) {
        parameters.add(new Parameter(name, value));
    }

    public String getName() {
        return name;
    }
}
