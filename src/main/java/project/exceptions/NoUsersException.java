package project.exceptions;

public class NoUsersException extends RuntimeException {
    public NoUsersException() {
        super("Error: NO USERS FOUND.\n");
    }
}
