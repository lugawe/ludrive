package app.ludrive.core.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable t) {
        super(message, t);
    }

    public AccessDeniedException(Throwable t) {
        super(t);
    }
}
