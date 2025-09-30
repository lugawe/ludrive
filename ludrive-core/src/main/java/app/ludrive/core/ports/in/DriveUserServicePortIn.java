package app.ludrive.core.ports.in;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.DriveUserServicePort;

public interface DriveUserServicePortIn extends DriveUserServicePort {

    DriveUser createDriveUser(DriveUser driveUser);

    DriveUser getDriveUser(AuthIdentity identity, UUID driveUserId);

    DriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, DriveUser driveUser);

    DriveUser deleteDriveUser(AuthIdentity identity, UUID driveUserId);
}
