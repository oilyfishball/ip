package Exceptions.Task.InvalidEvent;

public class InvalidEventFromException extends InvalidEventException{
    public InvalidEventFromException() {
        super("Please include /from");
    }
}
