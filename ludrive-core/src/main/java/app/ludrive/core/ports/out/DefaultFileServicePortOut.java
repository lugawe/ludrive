package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

public final class DefaultFileServicePortOut implements FileServicePortOut {

    private final Logger logger;
    private final MigrationHandler migrationHandler;
    private final FileRepository fileRepository;
    private final VirtualFileSystemService virtualFileSystemService;

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
    public File createFile(DriveUser driveUser, UUID entryId, File file) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createFile(file);

        return fileRepository.createFile(driveUser, file);
    }

    @Override
    public File createFile(DriveUser driveUser, UUID entryId, File file, Content content) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.createFile(file);
        virtualFileSystemService.updateFileContent(file.getPath(), content);

        return fileRepository.createFile(driveUser, file);
    }

    @Override
    public Stream<File> getFiles(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return fileRepository.getFiles(driveUser, path);
    }

    @Override
    public File getFile(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        return fileRepository.getFile(driveUser, path);
    }

    @Override
    public FileContent getFileContent(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        File file = fileRepository.getFile(driveUser, path);
        Content content = virtualFileSystemService.getFileContent(path);

        return new FileContent(file, content);
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File updatedFile) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(driveUser, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, updatedFile);

        return fileRepository.updateFile(driveUser, originalFilePath, updatedFile);
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File updatedFile, Content updatedContent) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(driveUser, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, updatedFile);
        virtualFileSystemService.updateFileContent(originalFilePath, updatedContent);

        return fileRepository.updateFile(driveUser, originalFilePath, updatedFile);
    }

    @Override
    public File deleteFile(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.deleteFile(path);

        return fileRepository.deleteFile(driveUser, path);
    }
}
