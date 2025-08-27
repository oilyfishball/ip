package ackermann.exceptions;

public class InvalidTargetException extends CheckedException {
    public InvalidTargetException() {
        super("Unable to locate task!");
    }
}
