package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.in.DefaultDriveUserServicePortIn;
import app.ludrive.core.ports.in.DriveUserServicePortIn;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.server.cdi.util.ClassNamed;

@ApplicationScoped
public class DriveUserServicePortInProducer {

    @Inject
    @ClassNamed(DriveUserServicePortIn.class)
    private Logger logger;

    @Inject
    private AuthService authService;

    @Inject
    private Validator validator;

    @Inject
    private EventManager eventManager;

    @Inject
    private DriveUserServicePortOut driveUserServicePortOut;

    public DriveUserServicePortInProducer() {}

    @Produces
    public DriveUserServicePortIn produce() {

        return new DefaultDriveUserServicePortIn(logger, authService, validator, eventManager, driveUserServicePortOut);
    }
}
