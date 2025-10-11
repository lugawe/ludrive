package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

public class DefaultFileServicePortOut implements FileServicePortOut {

    protected final Logger logger;
    protected final MigrationHandler migrationHandler;
    protected final FileRepository fileRepository;
    protected final VirtualFileSystemService virtualFileSystemService;

    public DefaultFileServicePortOut(
            Logger logger,
            MigrationHandler migrationHandler,
            FileRepository fileRepository,
            VirtualFileSystemService virtualFileSystemService) {
        this.logger = logger;
        this.migrationHandler = migrationHandler;
        this.fileRepository = fileRepository;
        this.virtualFileSystemService = virtualFileSystemService;
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, File file) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createFile(file);

        return fileRepository.createFile(identity, file);
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, File file, Content content) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createFile(file);
        virtualFileSystemService.updateFileContent(file.getPath(), content);

        return fileRepository.createFile(identity, file);
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
        Content content = virtualFileSystemService.getFileContent(path);

        return new FileContent(file, content);
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(identity, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, file);

        return fileRepository.updateFile(identity, originalFilePath, file);
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file, Content content) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(identity, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, file);
        virtualFileSystemService.updateFileContent(originalFilePath, content);

        return fileRepository.updateFile(identity, originalFilePath, file);
    }

    @Override
    public File deleteFile(AuthIdentity identity, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.deleteFile(path);

        return fileRepository.deleteFile(identity, path);
    }
}
