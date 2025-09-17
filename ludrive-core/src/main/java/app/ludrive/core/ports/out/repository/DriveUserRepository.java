package app.ludrive.core.ports.out.repository;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;

public interface DriveUserRepository {

    DriveUser createDriveUser(DriveUser driveUser);

    DriveUser getDriveUser(UUID driveUserId);

    DriveUser updateDriveUser(UUID driveUserId, DriveUser driveUser);

    UUID deleteDriveUser(UUID driveUserId);
}
