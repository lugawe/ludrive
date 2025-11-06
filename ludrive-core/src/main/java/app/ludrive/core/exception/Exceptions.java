package app.ludrive.core.exception;

public final class Exceptions {

    private Exceptions() {}

    public static NullPointerException createNullPointer(String name) {
        return new NullPointerException("Value '" + name + "' cannot be null");
    }

    public static IllegalStateException createNotFound(Class<?> tClass, String search) {
        return new IllegalStateException(tClass.getName() + " not found for value '" + search + "'");
    }

    public static IllegalStateException createNotAllowed(String value) {
        return new IllegalStateException("Value '" + value + "' is not allowed");
    }
}
