package app.ludrive.core.logging;

import app.ludrive.core.util.Lazy;

public final class NoLogger implements Logger {

    private static final Lazy<NoLogger> instance = Lazy.of(NoLogger::new);

    private NoLogger() {}

    @Override
    public Class<?> getDeclaringClass() {
        return NoLogger.class;
    }

    @Override
    public String getName() {
        return "NoLogger";
    }

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

    public static Logger getInstance() {
        return instance.get();
    }
}
