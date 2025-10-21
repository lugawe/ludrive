package app.ludrive.core.ports.out.cache;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.event.AbstractEventListener;

// TODO
public final class CachedDriveUserServicePortOut implements DriveUserServicePortOut, AbstractEventListener {

    private final DriveUserServicePortOut driveUserServicePortOut;
    private final DriveUserServiceCache driveUserServiceCache;

    public CachedDriveUserServicePortOut(
            DriveUserServicePortOut driveUserServicePortOut, DriveUserServiceCache driveUserServiceCache) {
        this.driveUserServicePortOut = driveUserServicePortOut;
        this.driveUserServiceCache = driveUserServiceCache;
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
