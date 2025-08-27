package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

public class DefaultDirectoryServicePortOut implements DirectoryServicePortOut {

    protected final Logger logger;
    protected final DirectoryRepository directoryRepository;

    public DefaultDirectoryServicePortOut(Logger logger, DirectoryRepository directoryRepository) {
        this.logger = logger;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {

        return directoryRepository.createDirectory(identity, entryId, directory);
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {

        return directoryRepository.getDirectories(identity, entryId, path);
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {

        return directoryRepository.getDirectory(identity, entryId, path);
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {

        return directoryRepository.updateDirectory(identity, entryId, path, directory);
    }

    @Override
    public EntryItemId deleteDirectory(AuthIdentity identity, UUID entryId, String path) {

        return directoryRepository.deleteDirectory(identity, entryId, path);
    }
}
