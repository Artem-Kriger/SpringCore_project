package project.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Error: USER NOT FOUND.\n");
    }
}
