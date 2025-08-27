package app.ludrive.core.ports;

import java.io.InputStream;
import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;

public interface FileServicePort {

    File createFile(AuthIdentity identity, UUID entryId, File file, InputStream fileContent);

    Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path);

    File getFile(AuthIdentity identity, UUID entryId, String path);

    File updateFile(AuthIdentity identity, UUID entryId, String path, File file, InputStream fileContent);

    File updateFile(AuthIdentity identity, UUID entryId, String path, File file);

    EntryItemId deleteFile(AuthIdentity identity, UUID entryId, String path);
}
