package app.ludrive.core.ports.in;

import java.nio.channels.Channel;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.ports.out.FileServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public class DefaultFileServicePortIn implements FileServicePortIn {

    protected final Logger logger;
    protected final AuthService authService;
    protected final Validator validator;
    protected final EventManager eventManager;
    protected final FileServicePortOut fileServicePortOut;

    public DefaultFileServicePortIn(
            Logger logger,
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            FileServicePortOut fileServicePortOut) {
        this.logger = logger;
        this.authService = authService;
        this.validator = validator;
        this.eventManager = eventManager;
        this.fileServicePortOut = fileServicePortOut;
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, File file, Channel fileContent) {

        authService.checkEntryAccess(identity, entryId);
        validator.validateFile(file, fileContent);

        File result = fileServicePortOut.createFile(identity, entryId, file, fileContent);

        eventManager.onFileCreated(new Events.FileCreatedProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        Consumer<File> onFileRead = file -> {
            EntryItemId id = file.getId();
            eventManager.onFileRead(new Events.FileReadProps(identity, entryId, id));
        };

        return fileServicePortOut.getFiles(identity, entryId, path).peek(onFileRead);
    }

    @Override
    public File getFile(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        File result = fileServicePortOut.getFile(identity, entryId, path);

        eventManager.onFileRead(new Events.FileReadProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public Channel getFileContent(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        Channel result = fileServicePortOut.getFileContent(identity, entryId, path);

        eventManager.onFileRead(new Events.FileReadProps(identity, entryId, new EntryItemId(entryId, path)));

        return result;
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);
        validator.validateFile(file);

        File result = fileServicePortOut.updateFile(identity, entryId, path, file);

        eventManager.onFileUpdated(new Events.FileUpdatedProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public File updateFileContent(AuthIdentity identity, UUID entryId, String path, File file, Channel fileContent) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);
        validator.validateFile(file, fileContent);

        File result = fileServicePortOut.updateFileContent(identity, entryId, path, file, fileContent);

        eventManager.onFileUpdated(new Events.FileUpdatedProps(identity, entryId, result.getId()));

        return result;
    }

    @Override
    public EntryItemId deleteFile(AuthIdentity identity, UUID entryId, String path) {

        authService.checkEntryAccess(identity, entryId);
        validator.validatePath(path);

        EntryItemId result = fileServicePortOut.deleteFile(identity, entryId, path);

        eventManager.onFileDeleted(new Events.FileDeletedProps(identity, entryId, result));

        return result;
    }
}
