package app.ludrive.core.ports;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;

public interface FileServicePort {

    File createFile(DriveUser driveUser, UUID entryId, File file);

    File createFile(DriveUser driveUser, UUID entryId, File file, Content content);

    Stream<File> getFiles(DriveUser driveUser, UUID entryId, String path);

    File getFile(DriveUser driveUser, UUID entryId, String path);

    FileContent getFileContent(DriveUser driveUser, UUID entryId, String path);

    File updateFile(DriveUser driveUser, UUID entryId, String path, File file);

    File updateFile(DriveUser driveUser, UUID entryId, String path, File file, Content content);

    File deleteFile(DriveUser driveUser, UUID entryId, String path);
}
