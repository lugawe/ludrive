package app.ludrive.core.exception;

public class CryptoException extends RuntimeException {

    public CryptoException() {
        super();
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable t) {
        super(message, t);
    }

    public CryptoException(Throwable t) {
        super(t);
    }
}
