package exception;

public class NotEqualsPasswordException extends RuntimeException {
    public NotEqualsPasswordException(String message) {
        super(message);
    }
}
