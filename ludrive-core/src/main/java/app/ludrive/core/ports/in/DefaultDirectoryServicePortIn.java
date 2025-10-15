package app.ludrive.core.ports.in;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public class DefaultDirectoryServicePortIn implements DirectoryServicePortIn {

    protected final Logger logger;
    protected final AuthService authService;
    protected final Validator validator;
    protected final EventManager eventManager;
    protected final DirectoryServicePortOut directoryServicePortOut;

    public DefaultDirectoryServicePortIn(
            Logger logger,
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            DirectoryServicePortOut directoryServicePortOut) {
        this.logger = logger;
        this.authService = authService;
        this.validator = validator;
        this.eventManager = eventManager;
        this.directoryServicePortOut = directoryServicePortOut;
    }

    @Override
    public Directory createDirectory(DriveUser driveUser, UUID entryId, Directory directory) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validateDirectory(directory);

        Directory result = directoryServicePortOut.createDirectory(driveUser, entryId, directory);

        eventManager.onDirectoryCreated(new Events.DirectoryCreatedProps(driveUser, entryId, result));

        return result;
    }

    @Override
    public Stream<Directory> getDirectories(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        Consumer<Directory> onDirectoryRead =
                directory -> eventManager.onDirectoryRead(new Events.DirectoryReadProps(driveUser, entryId, directory));

        return directoryServicePortOut.getDirectories(driveUser, entryId, path).peek(onDirectoryRead);
    }

    @Override
    public Directory getDirectory(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        Directory result = directoryServicePortOut.getDirectory(driveUser, entryId, path);

        eventManager.onDirectoryRead(new Events.DirectoryReadProps(driveUser, entryId, result));

        return result;
    }

    @Override
    public Directory updateDirectory(DriveUser driveUser, UUID entryId, String path, Directory updatedDirectory) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);
        validator.validateDirectory(updatedDirectory);

        Directory oldDirectory = directoryServicePortOut.getDirectory(driveUser, entryId, path);
        Directory newDirectory = directoryServicePortOut.updateDirectory(driveUser, entryId, path, updatedDirectory);

        eventManager.onDirectoryUpdated(
                new Events.DirectoryUpdatedProps(driveUser, entryId, oldDirectory, newDirectory));

        return newDirectory;
    }

    @Override
    public Directory deleteDirectory(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        Directory result = directoryServicePortOut.deleteDirectory(driveUser, entryId, path);

        eventManager.onDirectoryDeleted(new Events.DirectoryDeletedProps(driveUser, entryId, result));

        return result;
    }
}
