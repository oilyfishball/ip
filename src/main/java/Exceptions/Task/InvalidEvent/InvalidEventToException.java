package Exceptions.Task.InvalidEvent;

public class InvalidEventToException extends InvalidEventException {
    public InvalidEventToException() {
        super("Please include /to");
    }
}
