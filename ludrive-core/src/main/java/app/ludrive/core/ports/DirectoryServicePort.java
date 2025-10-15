package app.ludrive.core.ports;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;

public interface DirectoryServicePort {

    Directory createDirectory(DriveUser driveUser, UUID entryId, Directory directory);

    Stream<Directory> getDirectories(DriveUser driveUser, UUID entryId, String path);

    Directory getDirectory(DriveUser driveUser, UUID entryId, String path);

    Directory updateDirectory(DriveUser driveUser, UUID entryId, String path, Directory directory);

    Directory deleteDirectory(DriveUser driveUser, UUID entryId, String path);
}
