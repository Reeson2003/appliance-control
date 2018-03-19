package ru.reeson2003.applianceControl.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
public class Action {
    private String name;
    private Map<String, String> parameters;

    public Action(String name) {
        this.name = name;
        this.parameters = new HashMap<>();
    }

    public Collection<Parameter> getParameters() {
        return parameters.entrySet().stream()
                .map(entry -> new Parameter(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    public Parameter getParameter(String name) {
        if (parameters.containsKey(name))
            return new Parameter(name, parameters.get(name));
        else
            return null;
    }

    public String getName() {
        return name;
    }
}
