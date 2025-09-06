package app.ludrive.core.exception;

public class VFSException extends RuntimeException {

    public VFSException() {
        super();
    }

    public VFSException(String message) {
        super(message);
    }

    public VFSException(String message, Throwable t) {
        super(message, t);
    }

    public VFSException(Throwable t) {
        super(t);
    }
}
