package app.ludrive.core.ports.out.repository;

import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.File;

public interface FileRepository {

    File createFile(DriveUser driveUser, File file);

    Stream<File> getFiles(DriveUser driveUser, String path);

    File getFile(DriveUser driveUser, String path);

    File updateFile(DriveUser driveUser, String path, File file);

    File deleteFile(DriveUser driveUser, String path);
}
