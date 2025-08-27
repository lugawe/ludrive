package app.ludrive.core.service.event;

public interface EventManager {

    void onEntryCreated(Events.EntryCreatedProps props);

    void onEntryRead(Events.EntryReadProps props);

    void onEntryUpdated(Events.EntryUpdatedProps props);

    void onEntryDeleted(Events.EntryDeletedProps props);

    void onDirectoryCreated(Events.DirectoryCreatedProps props);

    void onDirectoryRead(Events.DirectoryReadProps props);

    void onDirectoryUpdated(Events.DirectoryUpdatedProps props);

    void onDirectoryDeleted(Events.DirectoryDeletedProps props);

    void onFileCreated(Events.FileCreatedProps props);

    void onFileRead(Events.FileReadProps props);

    void onFileUpdated(Events.FileUpdatedProps props);

    void onFileDeleted(Events.FileDeletedProps props);
}
