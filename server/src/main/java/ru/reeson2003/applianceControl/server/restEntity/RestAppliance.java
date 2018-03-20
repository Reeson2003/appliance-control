package ru.reeson2003.applianceControl.server.restEntity;

import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface RestAppliance {
    Long getId();

    String getIdentifier();

    State getState();

    Collection<Action> getActions();
}
