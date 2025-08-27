package exceptions.task.InvalidEvent;

public class InvalidEventFromException extends InvalidEventException{
    public InvalidEventFromException() {
        super("Please include /from");
    }
}
