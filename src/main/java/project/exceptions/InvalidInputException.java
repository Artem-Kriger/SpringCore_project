package project.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(){
        super("Error: INVALID INPUT.\n");
    }
}
