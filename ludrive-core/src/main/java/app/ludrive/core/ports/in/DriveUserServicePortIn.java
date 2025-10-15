package app.ludrive.core.ports.in;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.DriveUserServicePort;

public interface DriveUserServicePortIn extends DriveUserServicePort {

    DriveUser createDriveUser(AuthIdentity identity, DriveUser driveUser);

    DriveUser getDriveUser(DriveUser driveUser, UUID driveUserId);

    DriveUser updateDriveUser(DriveUser driveUser, UUID driveUserId, DriveUser updatedDriveUser);

    DriveUser deleteDriveUser(DriveUser driveUser, UUID driveUserId);
}
