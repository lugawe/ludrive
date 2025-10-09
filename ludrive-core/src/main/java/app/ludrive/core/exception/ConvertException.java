package app.ludrive.core.exception;

public class ConvertException extends RuntimeException {

    public ConvertException() {
        super();
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable t) {
        super(message, t);
    }

    public ConvertException(Throwable t) {
        super(t);
    }
}
