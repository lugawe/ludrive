package app.ludrive.core.ports.out.migration;

import app.ludrive.core.exception.MigrationException;
import app.ludrive.core.util.Lazy;

public final class NoMigrationHandler implements MigrationHandler {

    private static final Lazy<NoMigrationHandler> instance = Lazy.of(NoMigrationHandler::new);

    private NoMigrationHandler() {}

    @Override
    public boolean needsMigration() {
        return false;
    }

    @Override
    public void migrate() throws MigrationException {}

    public static MigrationHandler getInstance() {
        return instance.get();
    }
}
