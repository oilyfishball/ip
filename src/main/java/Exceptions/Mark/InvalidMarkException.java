package Exceptions.Mark;

import Exceptions.CheckedException;

public class InvalidMarkException extends CheckedException {
    public InvalidMarkException() {
        super("Invalid Selection.\nE.g. mark 1, unmark 1");
    }
}
