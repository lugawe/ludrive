package app.ludrive.core.exception;

public final class Exceptions {

    private Exceptions() {}

    public static NullPointerException createNullPointer(String name) {
        return new NullPointerException("Value '" + name + "' cannot be null");
    }
}
