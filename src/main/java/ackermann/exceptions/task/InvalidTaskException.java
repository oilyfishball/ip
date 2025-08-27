package ackermann.exceptions.task;

import ackermann.exceptions.CheckedException;

public class InvalidTaskException extends CheckedException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
