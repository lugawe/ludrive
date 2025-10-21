package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.out.DefaultDriveUserServicePortOut;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.ports.out.cache.CachedDriveUserServicePortOut;
import app.ludrive.core.ports.out.cache.DriveUserServiceCache;
import app.ludrive.core.ports.out.cache.MemoryDriveUserServiceCache;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.core.service.logging.Logger;
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
    public DriveUserServiceCache driveUserServiceCache() {
        return new MemoryDriveUserServiceCache();
    }

    @Produces
    public DriveUserServicePortOut produce(DriveUserServiceCache driveUserServiceCache) {

        DriveUserServicePortOut driveUserServicePortOut =
                new DefaultDriveUserServicePortOut(logger, migrationHandler, driveUserRepository);

        return new CachedDriveUserServicePortOut(driveUserServicePortOut, driveUserServiceCache);
    }
}
