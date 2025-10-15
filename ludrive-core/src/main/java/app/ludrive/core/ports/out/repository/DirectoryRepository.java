package app.ludrive.core.ports.out.repository;

import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;

public interface DirectoryRepository {

    Directory createDirectory(DriveUser driveUser, Directory directory);

    Stream<Directory> getDirectories(DriveUser driveUser, String path);

    Directory getDirectory(DriveUser driveUser, String path);

    Directory updateDirectory(DriveUser driveUser, String path, Directory updatedDirectory);

    Directory deleteDirectory(DriveUser driveUser, String path);
}
