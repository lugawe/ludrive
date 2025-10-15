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
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File file) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(driveUser, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, file);

        return fileRepository.updateFile(driveUser, originalFilePath, file);
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File file, Content content) {

        migrationHandler.checkRunMigration();

        File originalFile = fileRepository.getFile(driveUser, path);
        String originalFilePath = originalFile.getPath();

        virtualFileSystemService.updateFile(originalFilePath, file);
        virtualFileSystemService.updateFileContent(originalFilePath, content);

        return fileRepository.updateFile(driveUser, originalFilePath, file);
    }

    @Override
    public File deleteFile(DriveUser driveUser, UUID entryId, String path) {

        migrationHandler.checkRunMigration();

        virtualFileSystemService.deleteFile(path);

        return fileRepository.deleteFile(driveUser, path);
    }
}
