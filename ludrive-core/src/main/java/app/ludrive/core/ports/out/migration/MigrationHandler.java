package app.ludrive.core.ports.out.migration;

public interface MigrationHandler {

    boolean needsMigration();

    void migrate();
}
