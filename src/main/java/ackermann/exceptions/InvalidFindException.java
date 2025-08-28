package ackermann.exceptions;

public class InvalidFindException extends CheckedException {
    public InvalidFindException() {
        super("Unable to find task!");
    }
}
