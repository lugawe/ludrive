package app.ludrive.core.ports.out.repository;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;

public interface FileRepository {

    File createFile(AuthIdentity identity, UUID entryId, File file);

    Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path);

    File getFile(AuthIdentity identity, UUID entryId, String path);

    File updateFile(AuthIdentity identity, UUID entryId, String path, File file);

    File deleteFile(AuthIdentity identity, UUID entryId, String path);
}
