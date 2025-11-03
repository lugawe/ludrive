package app.ludrive.core.service.validation;

import java.util.regex.Pattern;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.ValidationException;

public class DefaultValidator implements Validator {

    private static final String VALID_PATH_REGEX = "^(/(?!\\.{1,2}($|/))[^/]+)+$";

    private static final String PARENT_PATH_REGEX = "/[^/]+/?$";

    private static final Pattern validPathPattern = Pattern.compile(VALID_PATH_REGEX);

    public DefaultValidator() {}

    protected String getParentPath(String path) throws ValidationException {
        validatePath(path);
        if (path.equals(Directory.ROOT)) {
            return null;
        }
        String result = path.replaceAll(PARENT_PATH_REGEX, "");
        if (result.isEmpty()) {
            return Directory.ROOT;
        }
        return result;
    }

    protected boolean isValidPath(String path) {
        return path != null
                && !path.isBlank()
                && path.length() < 4096
                && (Directory.ROOT.equals(path)
                        || validPathPattern.matcher(path).matches());
    }

    @Override
    public void validateDriveUser(DriveUser driveUser) throws ValidationException {}

    @Override
    public void validateEntry(Entry entry) throws ValidationException {}

    @Override
    public void validatePath(String path) throws ValidationException {
        if (!isValidPath(path)) {
            throw new ValidationException("Invalid path");
        }
    }

    @Override
    public void validateDirectory(Directory directory) throws ValidationException {
        validatePath(directory.getPath());
    }

    @Override
    public void validateFile(File file) throws ValidationException {
        validatePath(file.getPath());
    }

    @Override
    public void validateFile(File file, Content content) throws ValidationException {
        validatePath(file.getPath());
    }

    @Override
    public String normalizePath(String path) {

        if (path == null) {
            throw new NullPointerException("Parameter path cannot be null");
        }

        if (!isValidPath(path)) {
            throw new ValidationException("Invalid path");
        }

        String normalized = path.trim();
        if (normalized.charAt(0) != '/') {
            normalized = "/" + normalized;
        }

        if (normalized.length() > 1 && normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }

        return normalized;
    }
}
