package app.ludrive.core.logging;

public abstract class AbstractLogger implements Logger {

    public AbstractLogger() {}

    @Override
    public void putContext(String key, String context) {}

    @Override
    public void debug(String message) {}

    @Override
    public void debug(String message, Object... args) {}

    @Override
    public void debug(String message, Throwable t) {}

    @Override
    public void info(String message) {}

    @Override
    public void info(String message, Object... args) {}

    @Override
    public void info(String message, Throwable t) {}

    @Override
    public void warn(String message) {}

    @Override
    public void warn(String message, Object... args) {}

    @Override
    public void warn(String message, Throwable t) {}

    @Override
    public void error(String message) {}

    @Override
    public void error(String message, Object... args) {}

    @Override
    public void error(String message, Throwable t) {}
}
