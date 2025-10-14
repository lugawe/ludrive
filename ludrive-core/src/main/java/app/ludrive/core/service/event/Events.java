package app.ludrive.core.service.event;

import java.io.Serializable;
import java.util.Collections;
import java.util.SequencedCollection;
import java.util.UUID;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.util.Update;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;

public final class Events {

    public interface EventProps extends Serializable {

        AuthIdentity identity();
    }

    public record DriveUserCreatedProps(AuthIdentity identity, SequencedCollection<DriveUser> driveUsers)
            implements EventProps {
        public DriveUserCreatedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record DriveUserReadProps(AuthIdentity identity, SequencedCollection<DriveUser> driveUsers)
            implements EventProps {
        public DriveUserReadProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record DriveUserUpdatedProps(AuthIdentity identity, SequencedCollection<Update<DriveUser>> driveUsers)
            implements EventProps {

        public DriveUserUpdatedProps(AuthIdentity identity, Update<DriveUser> driveUserUpdate) {
            this(identity, Collections.singletonList(driveUserUpdate));
        }

        public DriveUserUpdatedProps(AuthIdentity identity, DriveUser oldDriveUser, DriveUser newDriveUser) {
            this(identity, new Update<>(oldDriveUser, newDriveUser));
        }
    }

    public record DriveUserDeletedProps(AuthIdentity identity, SequencedCollection<DriveUser> driveUsers)
            implements EventProps {
        public DriveUserDeletedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(driveUser));
        }
    }

    public record EntryCreatedProps(AuthIdentity identity, SequencedCollection<Entry> entries) implements EventProps {
        public EntryCreatedProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record EntryReadProps(AuthIdentity identity, SequencedCollection<Entry> entries) implements EventProps {
        public EntryReadProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record EntryUpdatedProps(AuthIdentity identity, SequencedCollection<Update<Entry>> entries)
            implements EventProps {

        public EntryUpdatedProps(AuthIdentity identity, Update<Entry> entryUpdate) {
            this(identity, Collections.singletonList(entryUpdate));
        }

        public EntryUpdatedProps(AuthIdentity identity, Entry oldEntry, Entry newEntry) {
            this(identity, new Update<>(oldEntry, newEntry));
        }
    }

    public record EntryDeletedProps(AuthIdentity identity, SequencedCollection<Entry> entries) implements EventProps {
        public EntryDeletedProps(AuthIdentity identity, Entry entry) {
            this(identity, Collections.singletonList(entry));
        }
    }

    public record DirectoryCreatedProps(AuthIdentity identity, UUID entryId, SequencedCollection<Directory> directories)
            implements EventProps {
        public DirectoryCreatedProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record DirectoryReadProps(AuthIdentity identity, UUID entryId, SequencedCollection<Directory> directories)
            implements EventProps {
        public DirectoryReadProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record DirectoryUpdatedProps(
            AuthIdentity identity, UUID entryId, SequencedCollection<Update<Directory>> directories)
            implements EventProps {

        public DirectoryUpdatedProps(AuthIdentity identity, UUID entryId, Update<Directory> directoryUpdate) {
            this(identity, entryId, Collections.singletonList(directoryUpdate));
        }

        public DirectoryUpdatedProps(
                AuthIdentity identity, UUID entryId, Directory oldDirectory, Directory newDirectory) {
            this(identity, entryId, new Update<>(oldDirectory, newDirectory));
        }
    }

    public record DirectoryDeletedProps(AuthIdentity identity, UUID entryId, SequencedCollection<Directory> directories)
            implements EventProps {
        public DirectoryDeletedProps(AuthIdentity identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(directory));
        }
    }

    public record FileCreatedProps(AuthIdentity identity, UUID entryId, SequencedCollection<File> files)
            implements EventProps {
        public FileCreatedProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    public record FileReadProps(AuthIdentity identity, UUID entryId, SequencedCollection<File> files)
            implements EventProps {
        public FileReadProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    public record FileUpdatedProps(AuthIdentity identity, UUID entryId, SequencedCollection<Update<File>> files)
            implements EventProps {

        public FileUpdatedProps(AuthIdentity identity, UUID entryId, Update<File> fileUpdate) {
            this(identity, entryId, Collections.singletonList(fileUpdate));
        }

        public FileUpdatedProps(AuthIdentity identity, UUID entryId, File oldFile, File newFile) {
            this(identity, entryId, new Update<>(oldFile, newFile));
        }
    }

    public record FileDeletedProps(AuthIdentity identity, UUID entryId, SequencedCollection<File> files)
            implements EventProps {
        public FileDeletedProps(AuthIdentity identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(file));
        }
    }

    private Events() {}
}
