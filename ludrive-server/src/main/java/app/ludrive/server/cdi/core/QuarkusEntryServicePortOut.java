package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.DefaultEntryServicePortOut;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.ports.out.repository.EntryRepository;
import app.ludrive.server.cdi.util.ClassNamed;

@ApplicationScoped
public class QuarkusEntryServicePortOut {

    @Inject
    @ClassNamed(EntryServicePortOut.class)
    private Logger logger;

    @Inject
    private EntryRepository entryRepository;

    public QuarkusEntryServicePortOut() {}

    @Produces
    public EntryServicePortOut produce() {

        return new DefaultEntryServicePortOut(logger, entryRepository);
    }
}
