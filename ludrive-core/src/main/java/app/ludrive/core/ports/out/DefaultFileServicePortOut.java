package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFSService;

public class DefaultFileServicePortOut implements FileServicePortOut {

    protected final Logger logger;
    protected final MigrationHandler migrationHandler;
    protected final FileRepository fileRepository;
    protected final VirtualFSService virtualFSService;

    public DefaultFileServicePortOut(
            Logger logger,
            MigrationHandler migrationHandler,
            FileRepository fileRepository,
            VirtualFSService virtualFSService) {
        this.logger = logger;
        this.migrationHandler = migrationHandler;
        this.fileRepository = fileRepository;
        this.virtualFSService = virtualFSService;
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, FileContent fileContent) {

        migrationHandler.checkRunMigration();

        virtualFSService.createFile(fileContent);

        return fileRepository.createFile(identity, fileContent.file());
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return fileRepository.getFiles(identity, path);
    }

    @Override
    public File getFile(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return fileRepository.getFile(identity, path);
    }

    @Override
    public FileContent getFileContent(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        File file = fileRepository.getFile(identity, path);

        return virtualFSService.getFileContent(file);
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        migrationHandler.checkRunMigration();

        virtualFSService.updateFile(path, file);

        return fileRepository.updateFile(identity, path, file);
    }

    @Override
    public File updateFileContent(AuthIdentity identity, UUID entryId, String path, FileContent fileContent) {

        migrationHandler.checkRunMigration();

        virtualFSService.updateFileContent(path, fileContent);

        return fileRepository.updateFile(identity, path, fileContent.file());
    }

    @Override
    public File deleteFile(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFSService.deleteFile(path);

        return fileRepository.deleteFile(identity, path);
    }
}
