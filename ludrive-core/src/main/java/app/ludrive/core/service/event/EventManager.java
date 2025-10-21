package app.ludrive.core.service.event;

public interface EventManager {

    void triggerDriveUserCreated(Events.DriveUserCreatedProps props);

    void triggerDriveUserRead(Events.DriveUserReadProps props);

    void triggerDriveUserUpdated(Events.DriveUserUpdatedProps props);

    void triggerDriveUserDeleted(Events.DriveUserDeletedProps props);

    void triggerEntryCreated(Events.EntryCreatedProps props);

    void triggerEntryRead(Events.EntryReadProps props);

    void triggerEntryUpdated(Events.EntryUpdatedProps props);

    void triggerEntryDeleted(Events.EntryDeletedProps props);

    void triggerDirectoryCreated(Events.DirectoryCreatedProps props);

    void triggerDirectoryRead(Events.DirectoryReadProps props);

    void triggerDirectoryUpdated(Events.DirectoryUpdatedProps props);

    void triggerDirectoryDeleted(Events.DirectoryDeletedProps props);

    void triggerFileCreated(Events.FileCreatedProps props);

    void triggerFileRead(Events.FileReadProps props);

    void triggerFileUpdated(Events.FileUpdatedProps props);

    void triggerFileDeleted(Events.FileDeletedProps props);
}
