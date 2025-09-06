package app.ludrive.core.ports;

import java.nio.channels.Channel;
import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;

public interface FileServicePort {

    File createFile(AuthIdentity identity, UUID entryId, File file, Channel fileContent);

    Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path);

    File getFile(AuthIdentity identity, UUID entryId, String path);

    Channel getFileContent(AuthIdentity identity, UUID entryId, String path);

    File updateFile(AuthIdentity identity, UUID entryId, String path, File file);

    File updateFileContent(AuthIdentity identity, UUID entryId, String path, File file, Channel fileContent);

    EntryItemId deleteFile(AuthIdentity identity, UUID entryId, String path);
}
