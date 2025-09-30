package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.migration.NoMigrationHandler;

// TODO should be request scoped
@ApplicationScoped
public class MigrationHandlerProducer {

    public MigrationHandlerProducer() {}

    @Produces
    public MigrationHandler produce() {
        return NoMigrationHandler.getInstance();
    }
}
