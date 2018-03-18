package ru.reeson2003.applianceControl.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface State extends Serializable {
    String getSatateName();

    Collection<Parameter> getParameters();
}
