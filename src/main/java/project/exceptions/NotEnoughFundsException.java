package project.exceptions;

public class NotEnoughFundsException extends RuntimeException{
    public NotEnoughFundsException(){
        super("Error: NOT ENOUGH FUNDS.\n");
    }
}
