package ackermann.exceptions.task.InvalidDeadline;

public class InvalidDeadlineByException extends InvalidDeadlineException{

    public InvalidDeadlineByException() {
        super("Please include the deadline using /by.\nE.g. deadline return book /by 2025-12-01");
    }
}
