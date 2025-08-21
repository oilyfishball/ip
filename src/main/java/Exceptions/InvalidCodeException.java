package Exceptions;

public class InvalidCodeException extends CheckedException{
    public InvalidCodeException() {
        super("No Such Codeword!");
    }
}
