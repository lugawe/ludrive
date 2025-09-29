package app.ludrive.core.service.logging;

import app.ludrive.core.util.Lazy;

public final class NoLogger extends AbstractLogger {

    private static final Lazy<NoLogger> instance = Lazy.of(NoLogger::new);

    private NoLogger() {}

    @Override
    public String getName() {
        return "NoLogger";
    }

    public static Logger getInstance() {
        return instance.get();
    }
}
