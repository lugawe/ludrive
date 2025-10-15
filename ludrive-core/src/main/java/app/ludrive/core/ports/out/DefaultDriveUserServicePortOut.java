package app.ludrive.core.ports.out;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.core.service.logging.Logger;

public class DefaultDriveUserServicePortOut implements DriveUserServicePortOut {

    protected final Logger logger;
    protected final MigrationHandler migrationHandler;
    protected final DriveUserRepository driveUserRepository;

    public DefaultDriveUserServicePortOut(
            Logger logger, MigrationHandler migrationHandler, DriveUserRepository driveUserRepository) {
        this.logger = logger;
        this.migrationHandler = migrationHandler;
        this.driveUserRepository = driveUserRepository;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {

        return driveUserRepository.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(UUID driveUserId) {

        return driveUserRepository.getDriveUser(driveUserId);
    }

    @Override
    public DriveUser updateDriveUser(UUID driveUserId, DriveUser updatedDriveUser) {

        return driveUserRepository.updateDriveUser(driveUserId, updatedDriveUser);
    }

    @Override
    public DriveUser deleteDriveUser(UUID driveUserId) {

        return driveUserRepository.deleteDriveUser(driveUserId);
    }
}
