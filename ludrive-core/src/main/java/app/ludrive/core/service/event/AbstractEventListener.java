package app.ludrive.core.service.event;

public interface AbstractEventListener extends EventListener {

    @Override
    default void onDriveUserCreated(Events.DriveUserCreatedProps props) {}

    @Override
    default void onDriveUserRead(Events.DriveUserReadProps props) {}

    @Override
    default void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {}

    @Override
    default void onDriveUserDeleted(Events.DriveUserDeletedProps props) {}

    @Override
    default void onEntryCreated(Events.EntryCreatedProps props) {}

    @Override
    default void onEntryRead(Events.EntryReadProps props) {}

    @Override
    default void onEntryUpdated(Events.EntryUpdatedProps props) {}

    @Override
    default void onEntryDeleted(Events.EntryDeletedProps props) {}

    @Override
    default void onDirectoryCreated(Events.DirectoryCreatedProps props) {}

    @Override
    default void onDirectoryRead(Events.DirectoryReadProps props) {}

    @Override
    default void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {}

    @Override
    default void onDirectoryDeleted(Events.DirectoryDeletedProps props) {}

    @Override
    default void onFileCreated(Events.FileCreatedProps props) {}

    @Override
    default void onFileRead(Events.FileReadProps props) {}

    @Override
    default void onFileUpdated(Events.FileUpdatedProps props) {}

    @Override
    default void onFileDeleted(Events.FileDeletedProps props) {}
}
