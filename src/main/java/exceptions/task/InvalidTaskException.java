package exceptions.task;

import exceptions.CheckedException;

public class InvalidTaskException extends CheckedException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
