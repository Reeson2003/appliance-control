package ru.reeson2003.applianceControl.api;

/**
 * @author Pavel Gavrilov
 */
public interface Prototype {
    Appliance getNew();

    Appliance getOld(State state);
}
