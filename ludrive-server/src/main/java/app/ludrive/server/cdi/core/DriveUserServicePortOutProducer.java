package app.ludrive.server.cdi.core;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.CachedDriveUserServicePortOut;
import app.ludrive.core.ports.out.DefaultDriveUserServicePortOut;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.cache.MemoryCache;
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
    public Cache<DriveUser, UUID> driveUserServiceCache() {
        return new MemoryCache<>();
    }

    @Produces
    public DriveUserServicePortOut produce(Cache<DriveUser, UUID> driveUserServiceCache) {

        DriveUserServicePortOut driveUserServicePortOut =
                new DefaultDriveUserServicePortOut(logger, migrationHandler, driveUserRepository);

        return new CachedDriveUserServicePortOut(driveUserServicePortOut, driveUserServiceCache);
    }
}
