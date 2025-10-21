package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.event.AbstractEventManager;

// TODO
public final class CachedFileServicePortOut implements FileServicePortOut, AbstractEventManager {

    private final FileServicePortOut fileServicePortOut;
    private final Cache<File, String> fileCache;
    private final Cache<Content, String> contentCache;

    public CachedFileServicePortOut(
            FileServicePortOut fileServicePortOut, Cache<File, String> fileCache, Cache<Content, String> contentCache) {
        this.fileServicePortOut = fileServicePortOut;
        this.fileCache = fileCache;
        this.contentCache = contentCache;
    }

    @Override
    public File createFile(DriveUser driveUser, UUID entryId, File file) {
        return fileServicePortOut.createFile(driveUser, entryId, file);
    }

    @Override
    public File createFile(DriveUser driveUser, UUID entryId, File file, Content content) {
        return fileServicePortOut.createFile(driveUser, entryId, file, content);
    }

    @Override
    public Stream<File> getFiles(DriveUser driveUser, UUID entryId, String path) {
        return fileServicePortOut.getFiles(driveUser, entryId, path);
    }

    @Override
    public File getFile(DriveUser driveUser, UUID entryId, String path) {
        return fileServicePortOut.getFile(driveUser, entryId, path);
    }

    @Override
    public FileContent getFileContent(DriveUser driveUser, UUID entryId, String path) {
        return fileServicePortOut.getFileContent(driveUser, entryId, path);
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File updatedFile) {
        return fileServicePortOut.updateFile(driveUser, entryId, path, updatedFile);
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File updatedFile, Content updatedContent) {
        return fileServicePortOut.updateFile(driveUser, entryId, path, updatedFile, updatedContent);
    }

    @Override
    public File deleteFile(DriveUser driveUser, UUID entryId, String path) {
        return fileServicePortOut.deleteFile(driveUser, entryId, path);
    }
}
