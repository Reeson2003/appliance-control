package ru.reeson2003.applianceControl.api;

import java.io.Serializable;

/**
 * @author Pavel Gavrilov
 */
public class Parameter implements Serializable {
    private String name;
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '{' + name + ':' + value + '}';
    }
}
