package app.ludrive.core.ports.in;

import java.util.UUID;

import app.ludrive.core.domain.management.DriveUser;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.validation.Validator;

public class DefaultDriveUserServicePortIn implements DriveUserServicePortIn {

    protected final Logger logger;
    protected final AuthService authService;
    protected final Validator validator;
    protected final DriveUserServicePortOut driveUserServicePortOut;

    public DefaultDriveUserServicePortIn(
            Logger logger,
            AuthService authService,
            Validator validator,
            DriveUserServicePortOut driveUserServicePortOut) {
        this.logger = logger;
        this.authService = authService;
        this.validator = validator;
        this.driveUserServicePortOut = driveUserServicePortOut;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {

        return driveUserServicePortOut.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(AuthIdentity identity, UUID driveUserId) {

        authService.checkDriveUserAccess(identity, driveUserId);

        return driveUserServicePortOut.getDriveUser(identity, driveUserId);
    }

    @Override
    public DriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, DriveUser driveUser) {

        authService.checkDriveUserAccess(identity, driveUserId);

        return driveUserServicePortOut.updateDriveUser(identity, driveUserId, driveUser);
    }

    @Override
    public UUID deleteDriveUser(AuthIdentity identity, UUID driveUserId) {

        authService.checkDriveUserAccess(identity, driveUserId);

        return driveUserServicePortOut.deleteDriveUser(identity, driveUserId);
    }
}
