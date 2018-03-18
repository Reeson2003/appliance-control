package ru.reeson2003.applianceControl.api;

import java.io.Serializable;

/**
 * @author Pavel Gavrilov
 */
public interface Parameter extends Serializable {
    String getName();

    String getValue();
}
