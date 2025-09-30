package app.ludrive.core.service.validation;

import java.nio.channels.Channel;
import java.util.regex.Pattern;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.exception.ValidationException;

public class Validator {

    private static final String VALID_PATH_REGEX = "^(/(?!\\.{1,2}($|/))[^/]+)+$";

    private static final String PARENT_PATH_REGEX = "/[^/]+/?$";

    private static final Pattern validPathPattern = Pattern.compile(VALID_PATH_REGEX);

    public Validator() {}

    protected boolean isValidPath(String path) {
        return path != null
                && !path.isBlank()
                && path.length() < 4096
                && (Directory.ROOT.equals(path)
                        || validPathPattern.matcher(path).matches());
    }

    public void validatePath(String path) throws ValidationException {
        if (!isValidPath(path)) {
            throw new ValidationException("invalid path");
        }
    }

    public void validateEntry(Entry entry) throws ValidationException {}

    public void validateDirectory(Directory directory) throws ValidationException {
        validatePath(directory.getPath());
    }

    public void validateFile(FileContent fileContent) throws ValidationException {
        validateFile(fileContent.file(), fileContent.content());
    }

    public void validateFile(File file, Channel fileContent) throws ValidationException {
        validateFile(file);
    }

    public void validateFile(File file) throws ValidationException {
        validatePath(file.getPath());
    }

    public String getParentPath(String path) throws ValidationException {
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
}
