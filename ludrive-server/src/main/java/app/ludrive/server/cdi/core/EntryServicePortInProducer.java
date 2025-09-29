package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.in.DefaultEntryServicePortIn;
import app.ludrive.core.ports.in.EntryServicePortIn;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.server.cdi.util.ClassNamed;

@ApplicationScoped
public class EntryServicePortInProducer {

    @Inject
    @ClassNamed(EntryServicePortIn.class)
    private Logger logger;

    @Inject
    private AuthService authService;

    @Inject
    private Validator validator;

    @Inject
    private EventManager eventManager;

    @Inject
    private EntryServicePortOut entryServicePortOut;

    public EntryServicePortInProducer() {}

    @Produces
    public EntryServicePortIn produce() {

        return new DefaultEntryServicePortIn(logger, authService, validator, eventManager, entryServicePortOut);
    }
}
