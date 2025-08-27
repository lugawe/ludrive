package app.ludrive.core.ports;

import java.util.UUID;

import app.ludrive.core.domain.management.DriveUser;
import app.ludrive.core.domain.management.auth.AuthIdentity;

public interface DriveUserServicePort {

    DriveUser createDriveUser(DriveUser driveUser);

    DriveUser getDriveUser(AuthIdentity identity, UUID driveUserId);

    DriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, DriveUser driveUser);

    UUID deleteDriveUser(AuthIdentity identity, UUID driveUserId);
}
