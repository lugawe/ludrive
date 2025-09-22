package app.ludrive.core.ports.out.migration;

import app.ludrive.core.exception.MigrationException;

public interface MigrationHandler {

    boolean needsMigration();

    void migrate() throws MigrationException;
}
