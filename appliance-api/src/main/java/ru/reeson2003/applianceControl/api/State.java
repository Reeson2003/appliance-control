package ru.reeson2003.applianceControl.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public class State implements Serializable {
    private String name;
    private Collection<Parameter> parameters;

    public State(String name, Collection<Parameter> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public Collection<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
