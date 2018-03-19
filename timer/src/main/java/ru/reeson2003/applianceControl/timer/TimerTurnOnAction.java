package ru.reeson2003.applianceControl.timer;

/**
 * @author Pavel Gavrilov
 */
public class TimerTurnOnAction extends TimerAction {
    public TimerTurnOnAction() {
        super(TURN_ON);
        this.setParameter(TIME, "");
    }
}
