package app.ludrive.core.ports.out.repository;

import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;

public interface DirectoryRepository {

    Directory createDirectory(AuthIdentity identity, Directory directory);

    Stream<Directory> getDirectories(AuthIdentity identity, String path);

    Directory getDirectory(AuthIdentity identity, String path);

    Directory updateDirectory(AuthIdentity identity, String path, Directory directory);

    Directory deleteDirectory(AuthIdentity identity, String path);
}
