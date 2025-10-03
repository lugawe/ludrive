package app.ludrive.core.ports.out.repository;

import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;

public interface FileRepository {

    File createFile(AuthIdentity identity, File file);

    Stream<File> getFiles(AuthIdentity identity, String path);

    File getFile(AuthIdentity identity, String path);

    File updateFile(AuthIdentity identity, String path, File file);

    File deleteFile(AuthIdentity identity, String path);
}
