package controller;

public class ControllerWarningException extends Exception {
    private String warning;

    public ControllerWarningException(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}
