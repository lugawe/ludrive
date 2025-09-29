package app.ludrive.core.ports.in;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
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
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {

        authService.checkEntryAccess(identity, entryId);
        validator.validateDirectory(directory);

        Directory result = directoryServicePortOut.createDirectory(identity, entryId, directory);

        eventManager.onDirectoryCreated(new Events.DirectoryCreatedProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        Consumer<Directory> onDirectoryRead = directory -> {
            EntryItemId id = directory.getId();
            eventManager.onDirectoryRead(new Events.DirectoryReadProps(identity, entryId, id));
        };

        return directoryServicePortOut.getDirectories(identity, entryId, path).peek(onDirectoryRead);
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        Directory result = directoryServicePortOut.getDirectory(identity, entryId, path);

        eventManager.onDirectoryRead(new Events.DirectoryReadProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);
        validator.validateDirectory(directory);

        Directory result = directoryServicePortOut.updateDirectory(identity, entryId, path, directory);

        eventManager.onDirectoryUpdated(new Events.DirectoryUpdatedProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public EntryItemId deleteDirectory(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        EntryItemId result = directoryServicePortOut.deleteDirectory(identity, entryId, path);

        eventManager.onDirectoryDeleted(new Events.DirectoryDeletedProps(identity, entryId, result));

        return result;
    }
}
