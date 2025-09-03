package ackermann.exceptions.task.InvalidDeadline;

import ackermann.exceptions.task.InvalidTaskException;

/**
 * Exception for invalid deadline
 */
public class InvalidDeadlineException extends InvalidTaskException {
    //0 is no space, 1 is no /by
    public InvalidDeadlineException() {
        super("Invalid Deadline Input!");
    }

    public InvalidDeadlineException(String message) {
        super("Invalid Deadline Input!\n" + message);
    }
}
