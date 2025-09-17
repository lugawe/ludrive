package app.ludrive.core.service.event;

public abstract class AbstractEventManager implements EventManager {

    public AbstractEventManager() {}

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {}

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {}

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {}

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {}

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {}

    @Override
    public void onEntryRead(Events.EntryReadProps props) {}

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {}

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {}

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {}

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {}

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {}

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {}

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {}

    @Override
    public void onFileRead(Events.FileReadProps props) {}

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {}

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {}
}
