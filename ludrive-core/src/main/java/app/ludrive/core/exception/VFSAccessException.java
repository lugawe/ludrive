package app.ludrive.core.exception;

public class VFSAccessException extends RuntimeException {

    public VFSAccessException() {
        super();
    }

    public VFSAccessException(String message) {
        super(message);
    }

    public VFSAccessException(String message, Throwable t) {
        super(message, t);
    }

    public VFSAccessException(Throwable t) {
        super(t);
    }
}
