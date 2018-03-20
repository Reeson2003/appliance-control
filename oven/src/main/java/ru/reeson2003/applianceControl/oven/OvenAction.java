package ru.reeson2003.applianceControl.oven;

import ru.reeson2003.applianceControl.api.Action;

/**
 * @author Pavel Gavrilov
 */
public abstract class OvenAction extends Action implements ActionNames {
    public OvenAction(String name) {
        super(name);
    }
}
