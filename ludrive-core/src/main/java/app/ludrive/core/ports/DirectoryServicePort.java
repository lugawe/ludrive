package app.ludrive.core.ports;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;

public interface DirectoryServicePort {

    Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory);

    Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path);

    Directory getDirectory(AuthIdentity identity, UUID entryId, String path);

    Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory);

    EntryItemId deleteDirectory(AuthIdentity identity, UUID entryId, String path);
}
