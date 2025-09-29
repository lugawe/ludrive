package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.out.DefaultEntryServicePortOut;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.EntryRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.server.cdi.util.ClassNamed;

@ApplicationScoped
public class EntryServicePortOutProducer {

    @Inject
    @ClassNamed(EntryServicePortOut.class)
    private Logger logger;

    @Inject
    private MigrationHandler migrationHandler;

    @Inject
    private EntryRepository entryRepository;

    public EntryServicePortOutProducer() {}

    @Produces
    public EntryServicePortOut produce() {

        return new DefaultEntryServicePortOut(logger, migrationHandler, entryRepository);
    }
}
