package app.ludrive.adapters.out.persistence.vfs.h2;

import app.ludrive.core.exception.ValidationException;
import app.ludrive.core.service.validation.Validator;

public final class Validation {

    private static final Validator validator = new Validator();

    private Validation() {}

    public static boolean isValidPath(String path) {
        try {
            validator.validatePath(path);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    public static String getParentPath(String path) {
        return validator.getParentPath(path);
    }
}
