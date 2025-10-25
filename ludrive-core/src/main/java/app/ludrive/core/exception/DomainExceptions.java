package app.ludrive.core.exception;

import java.util.UUID;

public final class DomainExceptions {

    private DomainExceptions() {}

    public static NotFoundException createDriveUserNotFound(UUID driveUserId) {
        return new NotFoundException("DriveUser not found: " + driveUserId);
    }

    public static NotFoundException createEntryNotFound(UUID entryId) {
        return new NotFoundException("Entry not found: " + entryId);
    }

    public static NotFoundException createDirectoryNotFound(String path) {
        return new NotFoundException("Directory not found: " + path);
    }

    public static NotFoundException createFileNotFound(String path) {
        return new NotFoundException("File not found: " + path);
    }
}
