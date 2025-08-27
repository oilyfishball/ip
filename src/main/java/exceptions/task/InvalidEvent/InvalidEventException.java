package exceptions.task.InvalidEvent;

import exceptions.task.InvalidTaskException;

public class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super("Invalid Event Input!\nE.g. event project meeting /from 2025-12-01 /to 2025-12-03");
    }

    public InvalidEventException(String message) {
        super("Invalid Event Input!\n" + message + "\nE.g. event project meeting /from 2025-12-01 /to 2025-12-03");
    }
}
