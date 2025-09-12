package app.ludrive.core.service.telemetry;

import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;

public class TelemetryEventManager implements EventManager {

    protected final TelemetryService telemetryService;

    public TelemetryEventManager(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onEntryRead(Events.EntryReadProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onFileRead(Events.FileReadProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {
        telemetryService.countSuccess(props.identity());
    }

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {
        telemetryService.countSuccess(props.identity());
    }
}
