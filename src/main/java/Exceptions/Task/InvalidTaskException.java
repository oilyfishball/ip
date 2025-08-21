package Exceptions.Task;

import Exceptions.CheckedException;

public class InvalidTaskException extends CheckedException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
