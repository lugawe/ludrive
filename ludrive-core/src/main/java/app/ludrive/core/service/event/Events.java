package app.ludrive.core.service.event;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;

public final class Events {

    public interface EventProps {

        AuthIdentity identity();
    }

    public record DriveUserCreatedProps(AuthIdentity identity, Collection<UUID> driveUserIds) implements EventProps {
        public DriveUserCreatedProps(AuthIdentity identity, UUID driveUserId) {
            this(identity, Collections.singletonList(driveUserId));
        }
    }

    public record DriveUserReadProps(AuthIdentity identity, Collection<UUID> driveUserIds) implements EventProps {
        public DriveUserReadProps(AuthIdentity identity, UUID driveUserId) {
            this(identity, Collections.singletonList(driveUserId));
        }
    }

    public record DriveUserUpdatedProps(AuthIdentity identity, Collection<UUID> driveUserIds) implements EventProps {
        public DriveUserUpdatedProps(AuthIdentity identity, UUID driveUserId) {
            this(identity, Collections.singletonList(driveUserId));
        }
    }

    public record DriveUserDeletedProps(AuthIdentity identity, Collection<UUID> driveUserIds) implements EventProps {
        public DriveUserDeletedProps(AuthIdentity identity, UUID driveUserId) {
            this(identity, Collections.singletonList(driveUserId));
        }
    }

    public record EntryCreatedProps(AuthIdentity identity, Collection<UUID> entryIds) implements EventProps {
        public EntryCreatedProps(AuthIdentity identity, UUID entryId) {
            this(identity, Collections.singletonList(entryId));
        }
    }

    public record EntryReadProps(AuthIdentity identity, Collection<UUID> entryIds) implements EventProps {
        public EntryReadProps(AuthIdentity identity, UUID entryId) {
            this(identity, Collections.singletonList(entryId));
        }
    }

    public record EntryUpdatedProps(AuthIdentity identity, Collection<UUID> entryIds) implements EventProps {
        public EntryUpdatedProps(AuthIdentity identity, UUID entryId) {
            this(identity, Collections.singletonList(entryId));
        }
    }

    public record EntryDeletedProps(AuthIdentity identity, Collection<UUID> entryIds) implements EventProps {
        public EntryDeletedProps(AuthIdentity identity, UUID entryId) {
            this(identity, Collections.singletonList(entryId));
        }
    }

    public record DirectoryCreatedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> directoryIds)
            implements EventProps {
        public DirectoryCreatedProps(AuthIdentity identity, UUID entryId, EntryItemId directoryId) {
            this(identity, entryId, Collections.singletonList(directoryId));
        }
    }

    public record DirectoryReadProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> directoryIds)
            implements EventProps {
        public DirectoryReadProps(AuthIdentity identity, UUID entryId, EntryItemId directoryId) {
            this(identity, entryId, Collections.singletonList(directoryId));
        }
    }

    public record DirectoryUpdatedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> directoryIds)
            implements EventProps {
        public DirectoryUpdatedProps(AuthIdentity identity, UUID entryId, EntryItemId directoryId) {
            this(identity, entryId, Collections.singletonList(directoryId));
        }
    }

    public record DirectoryDeletedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> directoryIds)
            implements EventProps {
        public DirectoryDeletedProps(AuthIdentity identity, UUID entryId, EntryItemId directoryId) {
            this(identity, entryId, Collections.singletonList(directoryId));
        }
    }

    public record FileCreatedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> fileIds)
            implements EventProps {
        public FileCreatedProps(AuthIdentity identity, UUID entryId, EntryItemId fileId) {
            this(identity, entryId, Collections.singletonList(fileId));
        }
    }

    public record FileReadProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> fileIds)
            implements EventProps {
        public FileReadProps(AuthIdentity identity, UUID entryId, EntryItemId fileId) {
            this(identity, entryId, Collections.singletonList(fileId));
        }
    }

    public record FileUpdatedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> fileIds)
            implements EventProps {
        public FileUpdatedProps(AuthIdentity identity, UUID entryId, EntryItemId fileId) {
            this(identity, entryId, Collections.singletonList(fileId));
        }
    }

    public record FileDeletedProps(AuthIdentity identity, UUID entryId, Collection<EntryItemId> fileIds)
            implements EventProps {
        public FileDeletedProps(AuthIdentity identity, UUID entryId, EntryItemId fileId) {
            this(identity, entryId, Collections.singletonList(fileId));
        }
    }

    private Events() {}
}
