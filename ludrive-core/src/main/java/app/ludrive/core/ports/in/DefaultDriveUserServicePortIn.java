package app.ludrive.core.ports.in;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AnonymousAuthIdentity;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public class DefaultDriveUserServicePortIn implements DriveUserServicePortIn {

    protected final Logger logger;
    protected final AuthService authService;
    protected final Validator validator;
    protected final EventManager eventManager;
    protected final DriveUserServicePortOut driveUserServicePortOut;

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
    public DriveUser createDriveUser(DriveUser driveUser) {

        DriveUser result = driveUserServicePortOut.createDriveUser(driveUser);

        eventManager.onDriveUserCreated(
                new Events.DriveUserCreatedProps(AnonymousAuthIdentity.getInstance(), result.getId()));

        return result;
    }

    @Override
    public DriveUser getDriveUser(AuthIdentity identity, UUID driveUserId) {

        authService.checkDriveUserAccess(identity, driveUserId);

        DriveUser result = driveUserServicePortOut.getDriveUser(driveUserId);

        eventManager.onDriveUserRead(new Events.DriveUserReadProps(identity, result.getId()));

        return result;
    }

    @Override
    public DriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, DriveUser driveUser) {

        authService.checkDriveUserAccess(identity, driveUserId);

        DriveUser result = driveUserServicePortOut.updateDriveUser(driveUserId, driveUser);

        eventManager.onDriveUserUpdated(new Events.DriveUserUpdatedProps(identity, result.getId()));

        return result;
    }

    @Override
    public UUID deleteDriveUser(AuthIdentity identity, UUID driveUserId) {

        authService.checkDriveUserAccess(identity, driveUserId);

        UUID result = driveUserServicePortOut.deleteDriveUser(driveUserId);

        eventManager.onDriveUserDeleted(new Events.DriveUserDeletedProps(identity, result));

        return result;
    }
}
