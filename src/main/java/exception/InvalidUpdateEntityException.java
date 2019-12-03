package exception;

public class InvalidUpdateEntityException extends RuntimeException {
    public InvalidUpdateEntityException(String message) {
        super(message);
    }
}
