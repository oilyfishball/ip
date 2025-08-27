package exceptions.task.InvalidEvent;

public class InvalidEventToException extends InvalidEventException {
    public InvalidEventToException() {
        super("Please include /to in yyyy-MM-dd format");
    }
}
