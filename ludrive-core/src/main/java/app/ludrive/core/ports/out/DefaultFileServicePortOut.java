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

    protected void checkRunMigration() {

        logger.info("checking if migration is required");

        if (migrationHandler.needsMigration()) {

            logger.info("migration required for entry, starting migration");

            migrationHandler.migrate();

        } else {
            logger.info("migration is not required");
        }
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, FileContent fileContent) {

        checkRunMigration();

        return fileRepository.createFile(identity, entryId, fileContent.file());
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {

        checkRunMigration();

        return fileRepository.getFiles(identity, entryId, path);
    }

    @Override
    public File getFile(AuthIdentity identity, UUID entryId, String path) {

        checkRunMigration();

        return fileRepository.getFile(identity, entryId, path);
    }

    @Override
    public FileContent getFileContent(AuthIdentity identity, UUID entryId, String path) {

        checkRunMigration();

        return null;
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        checkRunMigration();

        return fileRepository.updateFile(identity, entryId, path, file);
    }

    @Override
    public File updateFileContent(AuthIdentity identity, UUID entryId, String path, FileContent fileContent) {

        checkRunMigration();

        return null;
    }

    @Override
    public File deleteFile(AuthIdentity identity, UUID entryId, String path) {

        checkRunMigration();

        return fileRepository.deleteFile(identity, entryId, path);
    }
}
