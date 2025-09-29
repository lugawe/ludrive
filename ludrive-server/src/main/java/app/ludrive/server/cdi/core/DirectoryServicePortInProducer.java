package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.in.DefaultDirectoryServicePortIn;
import app.ludrive.core.ports.in.DirectoryServicePortIn;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class DirectoryServicePortInProducer {

    @Inject
    @ClassNamed(DirectoryServicePortIn.class)
    private Logger logger;

    @Inject
    private AuthService authService;

    @Inject
    private Validator validator;

    @Inject
    private EventManager eventManager;

    @Inject
    private DirectoryServicePortOut directoryServicePortOut;

    public DirectoryServicePortInProducer() {}

    @Produces
    public DirectoryServicePortIn produce() {

        return new DefaultDirectoryServicePortIn(logger, authService, validator, eventManager, directoryServicePortOut);
    }
}
