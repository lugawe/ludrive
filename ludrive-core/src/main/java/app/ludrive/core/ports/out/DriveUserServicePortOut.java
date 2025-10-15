package app.ludrive.core.ports.out;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.DriveUserServicePort;

public interface DriveUserServicePortOut extends DriveUserServicePort {

    DriveUser createDriveUser(DriveUser driveUser);

    DriveUser getDriveUser(UUID driveUserId);

    DriveUser updateDriveUser(UUID driveUserId, DriveUser updatedDriveUser);

    DriveUser deleteDriveUser(UUID driveUserId);
}
