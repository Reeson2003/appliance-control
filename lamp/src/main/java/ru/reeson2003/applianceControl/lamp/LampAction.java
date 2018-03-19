package ru.reeson2003.applianceControl.lamp;

import ru.reeson2003.applianceControl.api.Action;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pavel Gavrilov
 */
public abstract class LampAction implements Action, ActionNames {
    @Override
    public Collection<String> getOptionNames() {
        return Collections.emptyList();
    }

    @Override
    public void setOption(String name, String value) {
    }
}
