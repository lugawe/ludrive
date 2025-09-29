package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.EntryRepository;
import app.ludrive.core.service.logging.Logger;

public class DefaultEntryServicePortOut implements EntryServicePortOut {

    protected final Logger logger;
    protected final MigrationHandler migrationHandler;
    protected final EntryRepository entryRepository;

    public DefaultEntryServicePortOut(
            Logger logger, MigrationHandler migrationHandler, EntryRepository entryRepository) {
        this.logger = logger;
        this.migrationHandler = migrationHandler;
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry createEntry(AuthIdentity identity, Entry entry) {

        return entryRepository.createEntry(identity, entry);
    }

    @Override
    public Stream<Entry> getEntries(AuthIdentity identity) {

        return entryRepository.getEntries(identity);
    }

    @Override
    public Entry getEntry(AuthIdentity identity, UUID entryId) {

        return entryRepository.getEntry(identity, entryId);
    }

    @Override
    public Entry updateEntry(AuthIdentity identity, UUID entryId, Entry entry) {

        return entryRepository.updateEntry(identity, entryId, entry);
    }

    @Override
    public UUID deleteEntry(AuthIdentity identity, UUID entryId) {

        return entryRepository.deleteEntry(identity, entryId);
    }
}
