package project.exceptions;

public class LastAccountException extends RuntimeException {
    public LastAccountException() {
        super("Error: YOU CANT CLOSE LAST ACCOUNT");
    }
}
