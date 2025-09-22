package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.DefaultDriveUserServicePortOut;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.server.cdi.util.ClassNamed;

@ApplicationScoped
public class DriveUserServicePortOutProducer {

    @Inject
    @ClassNamed(DriveUserServicePortOut.class)
    private Logger logger;

    @Inject
    private MigrationHandler migrationHandler;

    @Inject
    private DriveUserRepository driveUserRepository;

    public DriveUserServicePortOutProducer() {}

    @Produces
    public DriveUserServicePortOut produce() {

        return new DefaultDriveUserServicePortOut(logger, migrationHandler, driveUserRepository);
    }
}
