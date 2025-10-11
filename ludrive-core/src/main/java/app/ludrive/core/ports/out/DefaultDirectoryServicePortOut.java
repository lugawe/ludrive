package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DirectoryRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

public class DefaultDirectoryServicePortOut implements DirectoryServicePortOut {

    protected final Logger logger;
    protected final MigrationHandler migrationHandler;
    protected final DirectoryRepository directoryRepository;
    protected final VirtualFileSystemService virtualFileSystemService;

    public DefaultDirectoryServicePortOut(
            Logger logger,
            MigrationHandler migrationHandler,
            DirectoryRepository directoryRepository,
            VirtualFileSystemService virtualFileSystemService) {
        this.logger = logger;
        this.migrationHandler = migrationHandler;
        this.directoryRepository = directoryRepository;
        this.virtualFileSystemService = virtualFileSystemService;
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createDirectory(directory);

        return directoryRepository.createDirectory(identity, directory);
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return directoryRepository.getDirectories(identity, path);
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return directoryRepository.getDirectory(identity, path);
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.updateDirectory(path, directory);

        return directoryRepository.updateDirectory(identity, path, directory);
    }

    @Override
    public Directory deleteDirectory(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.deleteDirectory(path);

        return directoryRepository.deleteDirectory(identity, path);
    }
}
