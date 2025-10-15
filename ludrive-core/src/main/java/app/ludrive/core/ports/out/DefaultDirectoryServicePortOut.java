package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
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
    public Directory createDirectory(DriveUser driveUser, UUID entryId, Directory directory) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createDirectory(directory);

        return directoryRepository.createDirectory(driveUser, directory);
    }

    @Override
    public Stream<Directory> getDirectories(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return directoryRepository.getDirectories(driveUser, path);
    }

    @Override
    public Directory getDirectory(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return directoryRepository.getDirectory(driveUser, path);
    }

    @Override
    public Directory updateDirectory(DriveUser driveUser, UUID entryId, String path, Directory updatedDirectory) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.updateDirectory(path, updatedDirectory);

        return directoryRepository.updateDirectory(driveUser, path, updatedDirectory);
    }

    @Override
    public Directory deleteDirectory(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.deleteDirectory(path);

        return directoryRepository.deleteDirectory(driveUser, path);
    }
}
