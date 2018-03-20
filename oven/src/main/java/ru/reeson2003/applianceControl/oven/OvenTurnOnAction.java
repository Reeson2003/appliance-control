package ru.reeson2003.applianceControl.oven;

/**
 * @author Pavel Gavrilov
 */
public class OvenTurnOnAction extends OvenAction implements ParameterNames {
    public OvenTurnOnAction() {
        super(TURN_ON);
        setParameter(TEMPERATURE, "");
    }
}
