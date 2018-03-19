package ru.reeson2003.applianceConntrol.service.api.entity;

import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.State;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceEntity {
    Long getId();

    String getIdentifier();

    State getState();

    Collection<Action> getActions();
}
