package project.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(){
        super("Error: ACCOUNT NOT FOUND.\n");
    }
}
