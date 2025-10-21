package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.event.AbstractEventManager;

// TODO
public class CachedDirectoryServicePortOut implements DirectoryServicePortOut, AbstractEventManager {

    protected final DirectoryServicePortOut directoryServicePortOut;
    protected final Cache<Directory, String> cache;

    public CachedDirectoryServicePortOut(
            DirectoryServicePortOut directoryServicePortOut, Cache<Directory, String> cache) {
        this.directoryServicePortOut = directoryServicePortOut;
        this.cache = cache;
    }

    @Override
    public Directory createDirectory(DriveUser driveUser, UUID entryId, Directory directory) {
        return directoryServicePortOut.createDirectory(driveUser, entryId, directory);
    }

    @Override
    public Stream<Directory> getDirectories(DriveUser driveUser, UUID entryId, String path) {
        return directoryServicePortOut.getDirectories(driveUser, entryId, path);
    }

    @Override
    public Directory getDirectory(DriveUser driveUser, UUID entryId, String path) {
        return directoryServicePortOut.getDirectory(driveUser, entryId, path);
    }

    @Override
    public Directory updateDirectory(DriveUser driveUser, UUID entryId, String path, Directory updatedDirectory) {
        return directoryServicePortOut.updateDirectory(driveUser, entryId, path, updatedDirectory);
    }

    @Override
    public Directory deleteDirectory(DriveUser driveUser, UUID entryId, String path) {
        return directoryServicePortOut.deleteDirectory(driveUser, entryId, path);
    }
}
