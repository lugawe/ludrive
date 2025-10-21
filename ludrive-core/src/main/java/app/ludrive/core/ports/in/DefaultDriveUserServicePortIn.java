package app.ludrive.core.ports.in;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public final class DefaultDriveUserServicePortIn implements DriveUserServicePortIn {

    private final Logger logger;
    private final AuthService authService;
    private final Validator validator;
    private final EventManager eventManager;
    private final DriveUserServicePortOut driveUserServicePortOut;

    public DefaultDriveUserServicePortIn(
            Logger logger,
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            DriveUserServicePortOut driveUserServicePortOut) {
        this.logger = logger;
        this.authService = authService;
        this.validator = validator;
        this.eventManager = eventManager;
        this.driveUserServicePortOut = driveUserServicePortOut;
    }

    @Override
    public DriveUser createDriveUser(AuthIdentity identity, DriveUser driveUser) {

        validator.validateDriveUser(driveUser);

        DriveUser result = driveUserServicePortOut.createDriveUser(driveUser);

        eventManager.onDriveUserCreated(new Events.DriveUserCreatedProps(identity, result));

        return result;
    }

    @Override
    public DriveUser getDriveUser(DriveUser driveUser, UUID driveUserId) {

        authService.checkDriveUserAccess(driveUser, driveUserId);

        DriveUser result = driveUserServicePortOut.getDriveUser(driveUserId);

        eventManager.onDriveUserRead(new Events.DriveUserReadProps(driveUser, result));

        return result;
    }

    @Override
    public DriveUser updateDriveUser(DriveUser driveUser, UUID driveUserId, DriveUser updatedDriveUser) {

        authService.checkDriveUserAccess(driveUser, driveUserId);
        validator.validateDriveUser(updatedDriveUser);

        DriveUser oldDriveUser = driveUserServicePortOut.getDriveUser(driveUserId);
        DriveUser newDriveUser = driveUserServicePortOut.updateDriveUser(driveUserId, updatedDriveUser);

        eventManager.onDriveUserUpdated(new Events.DriveUserUpdatedProps(driveUser, oldDriveUser, newDriveUser));

        return newDriveUser;
    }

    @Override
    public DriveUser deleteDriveUser(DriveUser driveUser, UUID driveUserId) {

        authService.checkDriveUserAccess(driveUser, driveUserId);

        DriveUser result = driveUserServicePortOut.deleteDriveUser(driveUserId);

        eventManager.onDriveUserDeleted(new Events.DriveUserDeletedProps(driveUser, result));

        return result;
    }
}
