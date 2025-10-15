package app.ludrive.core.ports.out;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
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
    public Entry createEntry(DriveUser driveUser, Entry entry) {

        return entryRepository.createEntry(driveUser, entry);
    }

    @Override
    public Stream<Entry> getEntries(DriveUser driveUser) {

        return entryRepository.getEntries(driveUser);
    }

    @Override
    public Entry getEntry(DriveUser driveUser, UUID entryId) {

        return entryRepository.getEntry(driveUser, entryId);
    }

    @Override
    public Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry) {

        return entryRepository.updateEntry(driveUser, entryId, updatedEntry);
    }

    @Override
    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {

        return entryRepository.deleteEntry(driveUser, entryId);
    }
}
