package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.migration.NoMigrationHandler;

@RequestScoped
public class MigrationHandlerProducer {

    public MigrationHandlerProducer() {}

    @Produces
    public MigrationHandler produce() {
        return NoMigrationHandler.getInstance();
    }
}
