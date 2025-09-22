package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.in.DefaultFileServicePortIn;
import app.ludrive.core.ports.in.FileServicePortIn;
import app.ludrive.core.ports.out.FileServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class QuarkusFileServicePortIn {

    @Inject
    @ClassNamed(FileServicePortIn.class)
    private Logger logger;

    @Inject
    private AuthService authService;

    @Inject
    private Validator validator;

    @Inject
    private EventManager eventManager;

    @Inject
    private FileServicePortOut fileServicePortOut;

    public QuarkusFileServicePortIn() {}

    @Produces
    public FileServicePortIn produce() {

        return new DefaultFileServicePortIn(logger, authService, validator, eventManager, fileServicePortOut);
    }
}
