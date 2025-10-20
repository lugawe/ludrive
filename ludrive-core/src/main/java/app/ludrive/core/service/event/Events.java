package app.ludrive.core.service.event;

import java.io.Serializable;
import java.util.Collections;
import java.util.SequencedCollection;
import java.util.UUID;

import app.ludrive.core.domain.Identifiable;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;

public final class Events {

    public interface EventProps extends Serializable {

        AuthIdentity identity();
    }

    public record CreatedEvent<T extends Identifiable>(T value) {}

    public record ReadEvent<T extends Identifiable>(T value) {}

    public record UpdatedEvent<T extends Identifiable>(T oldValue, T newValue) {}

    public record DeletedEvent<T extends Identifiable>(T value) {}

    public record DriveUserCreatedProps(
            AuthIdentity identity, SequencedCollection<CreatedEvent<DriveUser>> createdDriveUsers)
            implements EventProps {

        public DriveUserCreatedProps(AuthIdentity identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(new CreatedEvent<>(driveUser)));
        }
    }

    public record DriveUserReadProps(DriveUser identity, SequencedCollection<ReadEvent<DriveUser>> readDriveUsers)
            implements EventProps {

        public DriveUserReadProps(DriveUser identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(new ReadEvent<>(driveUser)));
        }
    }

    public record DriveUserUpdatedProps(
            DriveUser identity, SequencedCollection<UpdatedEvent<DriveUser>> updatedDriveUsers) implements EventProps {

        public DriveUserUpdatedProps(DriveUser identity, DriveUser oldDriveUser, DriveUser newDriveUser) {
            this(identity, Collections.singletonList(new UpdatedEvent<>(oldDriveUser, newDriveUser)));
        }
    }

    public record DriveUserDeletedProps(
            DriveUser identity, SequencedCollection<DeletedEvent<DriveUser>> deletedDriveUsers) implements EventProps {

        public DriveUserDeletedProps(DriveUser identity, DriveUser driveUser) {
            this(identity, Collections.singletonList(new DeletedEvent<>(driveUser)));
        }
    }

    public record EntryCreatedProps(DriveUser identity, SequencedCollection<CreatedEvent<Entry>> createdEntries)
            implements EventProps {

        public EntryCreatedProps(DriveUser identity, Entry entry) {
            this(identity, Collections.singletonList(new CreatedEvent<>(entry)));
        }
    }

    public record EntryReadProps(DriveUser identity, SequencedCollection<ReadEvent<Entry>> readEntries)
            implements EventProps {

        public EntryReadProps(DriveUser identity, Entry entry) {
            this(identity, Collections.singletonList(new ReadEvent<>(entry)));
        }
    }

    public record EntryUpdatedProps(DriveUser identity, SequencedCollection<UpdatedEvent<Entry>> updatedEntries)
            implements EventProps {

        public EntryUpdatedProps(DriveUser identity, Entry oldEntry, Entry newEntry) {
            this(identity, Collections.singletonList(new UpdatedEvent<>(oldEntry, newEntry)));
        }
    }

    public record EntryDeletedProps(DriveUser identity, SequencedCollection<DeletedEvent<Entry>> deletedEntries)
            implements EventProps {

        public EntryDeletedProps(DriveUser identity, Entry entry) {
            this(identity, Collections.singletonList(new DeletedEvent<>(entry)));
        }
    }

    public record DirectoryCreatedProps(
            DriveUser identity, UUID entryId, SequencedCollection<CreatedEvent<Directory>> createdDirectories)
            implements EventProps {

        public DirectoryCreatedProps(DriveUser identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(new CreatedEvent<>(directory)));
        }
    }

    public record DirectoryReadProps(
            DriveUser identity, UUID entryId, SequencedCollection<ReadEvent<Directory>> readDirectories)
            implements EventProps {

        public DirectoryReadProps(DriveUser identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(new ReadEvent<>(directory)));
        }
    }

    public record DirectoryUpdatedProps(
            DriveUser identity, UUID entryId, SequencedCollection<UpdatedEvent<Directory>> updatedDirectories)
            implements EventProps {

        public DirectoryUpdatedProps(DriveUser identity, UUID entryId, Directory oldDirectory, Directory newDirectory) {
            this(identity, entryId, Collections.singletonList(new UpdatedEvent<>(oldDirectory, newDirectory)));
        }
    }

    public record DirectoryDeletedProps(
            DriveUser identity, UUID entryId, SequencedCollection<DeletedEvent<Directory>> deletedDirectories)
            implements EventProps {

        public DirectoryDeletedProps(DriveUser identity, UUID entryId, Directory directory) {
            this(identity, entryId, Collections.singletonList(new DeletedEvent<>(directory)));
        }
    }

    public record FileCreatedProps(
            DriveUser identity, UUID entryId, SequencedCollection<CreatedEvent<File>> createdFiles)
            implements EventProps {

        public FileCreatedProps(DriveUser identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(new CreatedEvent<>(file)));
        }
    }

    public record FileReadProps(DriveUser identity, UUID entryId, SequencedCollection<ReadEvent<File>> readFiles)
            implements EventProps {

        public FileReadProps(DriveUser identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(new ReadEvent<>(file)));
        }
    }

    public record FileUpdatedProps(
            DriveUser identity, UUID entryId, SequencedCollection<UpdatedEvent<File>> updatedFiles)
            implements EventProps {

        public FileUpdatedProps(DriveUser identity, UUID entryId, File oldFile, File newFile) {
            this(identity, entryId, Collections.singletonList(new UpdatedEvent<>(oldFile, newFile)));
        }
    }

    public record FileDeletedProps(
            DriveUser identity, UUID entryId, SequencedCollection<DeletedEvent<File>> deletedFiles)
            implements EventProps {

        public FileDeletedProps(DriveUser identity, UUID entryId, File file) {
            this(identity, entryId, Collections.singletonList(new DeletedEvent<>(file)));
        }
    }

    private Events() {}
}
