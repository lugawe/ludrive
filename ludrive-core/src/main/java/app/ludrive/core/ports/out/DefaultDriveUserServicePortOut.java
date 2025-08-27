package app.ludrive.core.ports.out;

import java.util.UUID;

import app.ludrive.core.domain.management.DriveUser;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.repository.DriveUserRepository;

public class DefaultDriveUserServicePortOut implements DriveUserServicePortOut {

    protected final Logger logger;
    protected final DriveUserRepository driveUserRepository;

    public DefaultDriveUserServicePortOut(Logger logger, DriveUserRepository driveUserRepository) {
        this.logger = logger;
        this.driveUserRepository = driveUserRepository;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {

        return driveUserRepository.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(AuthIdentity identity, UUID driveUserId) {

        return driveUserRepository.getDriveUser(driveUserId);
    }

    @Override
    public DriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, DriveUser driveUser) {

        return driveUserRepository.updateDriveUser(driveUserId, driveUser);
    }

    @Override
    public UUID deleteDriveUser(AuthIdentity identity, UUID driveUserId) {

        return driveUserRepository.deleteDriveUser(driveUserId);
    }
}
