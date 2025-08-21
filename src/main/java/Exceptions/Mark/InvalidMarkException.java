package Exceptions.Mark;

public class InvalidMarkException extends Exception {
    public InvalidMarkException() {
        super("Invalid Selection.\nE.g. mark 1, unmark 1");
    }
}
