package app.ludrive.core.ports.in;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
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
    public File createFile(DriveUser driveUser, UUID entryId, File file) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validateFile(file);

        File result = fileServicePortOut.createFile(driveUser, entryId, file);

        eventManager.onFileCreated(new Events.FileCreatedProps(driveUser, entryId, result));

        return result;
    }

    @Override
    public File createFile(DriveUser driveUser, UUID entryId, File file, Content content) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validateFile(file);
        validator.validateFile(content);

        File result = fileServicePortOut.createFile(driveUser, entryId, file, content);

        eventManager.onFileCreated(new Events.FileCreatedProps(driveUser, entryId, result));

        return result;
    }

    @Override
    public Stream<File> getFiles(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        Consumer<File> onFileRead = file -> eventManager.onFileRead(new Events.FileReadProps(driveUser, entryId, file));

        return fileServicePortOut.getFiles(driveUser, entryId, path).peek(onFileRead);
    }

    @Override
    public File getFile(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        File result = fileServicePortOut.getFile(driveUser, entryId, path);

        eventManager.onFileRead(new Events.FileReadProps(driveUser, entryId, result));

        return result;
    }

    @Override
    public FileContent getFileContent(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        FileContent result = fileServicePortOut.getFileContent(driveUser, entryId, path);

        eventManager.onFileRead(new Events.FileReadProps(driveUser, entryId, result.file()));

        return result;
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File file) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);
        validator.validateFile(file);

        File oldFile = fileServicePortOut.getFile(driveUser, entryId, path);
        File newFile = fileServicePortOut.updateFile(driveUser, entryId, path, file);

        eventManager.onFileUpdated(new Events.FileUpdatedProps(driveUser, entryId, oldFile, newFile));

        return newFile;
    }

    @Override
    public File updateFile(DriveUser driveUser, UUID entryId, String path, File file, Content content) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);
        validator.validateFile(file);
        validator.validateFile(content);

        File oldFile = fileServicePortOut.getFile(driveUser, entryId, path);
        File newFile = fileServicePortOut.updateFile(driveUser, entryId, path, file, content);

        eventManager.onFileUpdated(new Events.FileUpdatedProps(driveUser, entryId, oldFile, newFile));

        return newFile;
    }

    @Override
    public File deleteFile(DriveUser driveUser, UUID entryId, String path) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validatePath(path);

        File result = fileServicePortOut.deleteFile(driveUser, entryId, path);

        eventManager.onFileDeleted(new Events.FileDeletedProps(driveUser, entryId, result));

        return result;
    }
}
