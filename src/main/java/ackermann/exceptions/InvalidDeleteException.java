package ackermann.exceptions;

public class InvalidDeleteException extends CheckedException{
    public InvalidDeleteException() {
        super("Invalid Selection.\nE.g. delete 1");
    }
}
