package Exceptions.Mark;

public class InvalidTargetException extends Exception {
    public InvalidTargetException() {
        super("Unable to locate task!");
    }
}
