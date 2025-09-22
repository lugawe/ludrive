package app.ludrive.core.exception;

public class MigrationException extends RuntimeException {

    public MigrationException() {
        super();
    }

    public MigrationException(String message) {
        super(message);
    }

    public MigrationException(String message, Throwable t) {
        super(message, t);
    }

    public MigrationException(Throwable t) {
        super(t);
    }
}
