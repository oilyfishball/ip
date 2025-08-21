package Exceptions.Task.InvalidEvent;

import Exceptions.Task.InvalidTaskException;

public class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super("Invalid Event Input!\nE.g. event project meeting /from Mon 2pm /to 4pm");
    }

    public InvalidEventException(String message) {
        super("Invalid Event Input!\n" + message + "\nE.g. event project meeting /from Mon 2pm /to 4pm");
    }
}
