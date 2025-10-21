package app.ludrive.core.ports.out;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.event.AbstractEventManager;

// TODO
public class CachedDriveUserServicePortOut implements DriveUserServicePortOut, AbstractEventManager {

    protected final DriveUserServicePortOut driveUserServicePortOut;
    protected final Cache<DriveUser, UUID> cache;

    public CachedDriveUserServicePortOut(
            DriveUserServicePortOut driveUserServicePortOut, Cache<DriveUser, UUID> cache) {
        this.driveUserServicePortOut = driveUserServicePortOut;
        this.cache = cache;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {
        return driveUserServicePortOut.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(UUID driveUserId) {
        return driveUserServicePortOut.getDriveUser(driveUserId);
    }

    @Override
    public DriveUser updateDriveUser(UUID driveUserId, DriveUser updatedDriveUser) {
        return driveUserServicePortOut.updateDriveUser(driveUserId, updatedDriveUser);
    }

    @Override
    public DriveUser deleteDriveUser(UUID driveUserId) {
        return driveUserServicePortOut.deleteDriveUser(driveUserId);
    }
}
