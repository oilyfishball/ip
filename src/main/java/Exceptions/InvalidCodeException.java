package Exceptions;

public class InvalidCodeException extends Exception{
    public InvalidCodeException() {
        super("No Such Codeword!");
    }
}
