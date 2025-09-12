package app.ludrive.core.service.event;

import app.ludrive.core.logging.Logger;

public class LoggingEventManager implements EventManager {

    protected final Logger logger;

    public LoggingEventManager(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {
        logger.info("drive-user created {}", props);
    }

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {
        logger.info("drive-user read {}", props);
    }

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        logger.info("drive-user updated {}", props);
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {
        logger.info("drive-user deleted {}", props);
    }

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {
        logger.info("entry created {}", props);
    }

    @Override
    public void onEntryRead(Events.EntryReadProps props) {
        logger.info("entry read {}", props);
    }

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {
        logger.info("entry updated {}", props);
    }

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {
        logger.info("entry deleted {}", props);
    }

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {
        logger.info("directory created {}", props);
    }

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {
        logger.info("directory read {}", props);
    }

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {
        logger.info("directory updated {}", props);
    }

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {
        logger.info("directory deleted {}", props);
    }

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {
        logger.info("file created {}", props);
    }

    @Override
    public void onFileRead(Events.FileReadProps props) {
        logger.info("file read {}", props);
    }

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {
        logger.info("file updated {}", props);
    }

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {
        logger.info("file deleted {}", props);
    }
}
