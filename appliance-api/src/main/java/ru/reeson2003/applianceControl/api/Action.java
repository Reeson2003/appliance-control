package ru.reeson2003.applianceControl.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface Action extends Serializable {
    String getActionName();

    Collection<String> getOptionNames();

    void setOption(String name, String value);
}
