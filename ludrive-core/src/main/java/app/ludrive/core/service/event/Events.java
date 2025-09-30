package app.ludrive.core.service.event;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;

public final class Events {

    public interface EventProps {

        AuthIdentity identity();
    }

    public record DriveUserCreatedProps(AuthIdentity identity, Collection<DriveUser> driveUsers) implements EventProps {
        public DriveUserCreatedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record DriveUserReadProps(AuthIdentity identity, Collection<DriveUser> driveUsers) implements EventProps {
        public DriveUserReadProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record DriveUserUpdatedProps(AuthIdentity identity, Collection<DriveUser> driveUsers) implements EventProps {
        public DriveUserUpdatedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record DriveUserDeletedProps(AuthIdentity identity, Collection<DriveUser> driveUsers) implements EventProps {
        public DriveUserDeletedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record EntryCreatedProps(AuthIdentity identity, Collection<Entry> entries) implements EventProps {
        public EntryCreatedProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record EntryReadProps(AuthIdentity identity, Collection<Entry> entries) implements EventProps {
        public EntryReadProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record EntryUpdatedProps(AuthIdentity identity, Collection<Entry> entries) implements EventProps {
        public EntryUpdatedProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record EntryDeletedProps(AuthIdentity identity, Collection<Entry> entries) implements EventProps {
        public EntryDeletedProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record DirectoryCreatedProps(AuthIdentity identity, UUID entryId, Collection<Directory> directories)
            implements EventProps {
        public DirectoryCreatedProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record DirectoryReadProps(AuthIdentity identity, UUID entryId, Collection<Directory> directories)
            implements EventProps {
        public DirectoryReadProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record DirectoryUpdatedProps(AuthIdentity identity, UUID entryId, Collection<Directory> directories)
            implements EventProps {
        public DirectoryUpdatedProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record DirectoryDeletedProps(AuthIdentity identity, UUID entryId, Collection<Directory> directories)
            implements EventProps {
        public DirectoryDeletedProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record FileCreatedProps(AuthIdentity identity, UUID entryId, Collection<File> files) implements EventProps {
        public FileCreatedProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    public record FileReadProps(AuthIdentity identity, UUID entryId, Collection<File> files) implements EventProps {
        public FileReadProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    public record FileUpdatedProps(AuthIdentity identity, UUID entryId, Collection<File> files) implements EventProps {
        public FileUpdatedProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    public record FileDeletedProps(AuthIdentity identity, UUID entryId, Collection<File> files) implements EventProps {
        public FileDeletedProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    private Events() {}
}
