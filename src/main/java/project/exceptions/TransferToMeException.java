package project.exceptions;

public class TransferToMeException extends RuntimeException {
    public TransferToMeException(){
        super("Error: TRANSFER TO ME FAILED.\n");
    }
}
