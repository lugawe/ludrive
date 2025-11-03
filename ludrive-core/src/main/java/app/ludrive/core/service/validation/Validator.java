package app.ludrive.core.service.validation;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.ValidationException;

public interface Validator {

    void validateDriveUser(DriveUser driveUser) throws ValidationException;

    void validateEntry(Entry entry) throws ValidationException;

    void validatePath(String path) throws ValidationException;

    void validateDirectory(Directory directory) throws ValidationException;

    void validateFile(File file) throws ValidationException;

    void validateFile(File file, Content content) throws ValidationException;

    // ---

    String normalizePath(String path) throws ValidationException;
}
