package app.ludrive.core.exception;

public class CacheException extends RuntimeException {

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable t) {
        super(message, t);
    }

    public CacheException(Throwable t) {
        super(t);
    }
}
