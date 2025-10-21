package app.ludrive.core.ports.out.cache;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.service.event.AbstractEventManager;

// TODO
public final class CachedDirectoryServicePortOut implements DirectoryServicePortOut, AbstractEventManager {

    private final DirectoryServicePortOut directoryServicePortOut;
    private final DirectoryServiceCache directoryServiceCache;

    public CachedDirectoryServicePortOut(
            DirectoryServicePortOut directoryServicePortOut, DirectoryServiceCache directoryServiceCache) {
        this.directoryServicePortOut = directoryServicePortOut;
        this.directoryServiceCache = directoryServiceCache;
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
