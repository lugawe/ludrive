package app.ludrive.server.cdi.core;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.ports.out.CachedEntryServicePortOut;
import app.ludrive.core.ports.out.DefaultEntryServicePortOut;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.EntryRepository;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.cache.MemoryCache;
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
    public Cache<Entry, UUID> entryServiceCache() {
        return new MemoryCache<>();
    }

    @Produces
    public EntryServicePortOut produce(Cache<Entry, UUID> entryServiceCache) {

        EntryServicePortOut entryServicePortOut =
                new DefaultEntryServicePortOut(logger, migrationHandler, entryRepository);

        return new CachedEntryServicePortOut(entryServicePortOut, entryServiceCache);
    }
}
