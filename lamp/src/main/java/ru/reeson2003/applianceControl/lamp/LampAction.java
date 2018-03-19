package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.Action;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public abstract class LampAction extends Action implements ActionNames {

    public LampAction(String name) {
        super(name);
    }
}
