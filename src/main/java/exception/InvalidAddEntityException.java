package exception;

public class InvalidAddEntityException extends RuntimeException {
    public InvalidAddEntityException(String message) {
        super(message);
    }
}
