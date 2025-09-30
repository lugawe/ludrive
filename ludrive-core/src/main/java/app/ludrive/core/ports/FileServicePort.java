package app.ludrive.core.ports;

import java.nio.channels.Channel;
import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;

public interface FileServicePort {

    File createFile(AuthIdentity identity, UUID entryId, File file, Channel fileContent);

    Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path);

    File getFile(AuthIdentity identity, UUID entryId, String path);

    FileContent getFileContent(AuthIdentity identity, UUID entryId, String path);

    File updateFile(AuthIdentity identity, UUID entryId, String path, File file);

    File updateFileContent(AuthIdentity identity, UUID entryId, String path, FileContent fileContent);

    File deleteFile(AuthIdentity identity, UUID entryId, String path);
}
