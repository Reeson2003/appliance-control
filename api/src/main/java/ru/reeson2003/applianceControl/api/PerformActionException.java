package ru.reeson2003.applianceControl.api;

/**
 * @author Pavel Gavrilov
 */
public class PerformActionException extends Exception {
    public PerformActionException() {
    }

    public PerformActionException(String message) {
        super(message);
    }

    public PerformActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PerformActionException(Throwable cause) {
        super(cause);
    }

    public PerformActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
