package project.exceptions;

public class DuplicateLoginException extends RuntimeException{
    public DuplicateLoginException(){
        super("Error: USER ALREADY EXISTS.\n");
    }
}
