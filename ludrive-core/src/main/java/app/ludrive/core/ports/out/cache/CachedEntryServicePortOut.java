package app.ludrive.core.ports.out.cache;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.event.AbstractEventManager;

// TODO
public final class CachedEntryServicePortOut implements EntryServicePortOut, AbstractEventManager {

    private final EntryServicePortOut entryServicePortOut;
    private final EntryServiceCache entryServiceCache;

    public CachedEntryServicePortOut(EntryServicePortOut entryServicePortOut, EntryServiceCache entryServiceCache) {
        this.entryServicePortOut = entryServicePortOut;
        this.entryServiceCache = entryServiceCache;
    }

    @Override
    public Entry createEntry(DriveUser driveUser, Entry entry) {
        return entryServicePortOut.createEntry(driveUser, entry);
    }

    @Override
    public Stream<Entry> getEntries(DriveUser driveUser) {
        return entryServicePortOut.getEntries(driveUser);
    }

    @Override
    public Entry getEntry(DriveUser driveUser, UUID entryId) {
        return entryServicePortOut.getEntry(driveUser, entryId);
    }

    @Override
    public Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry) {
        return entryServicePortOut.updateEntry(driveUser, entryId, updatedEntry);
    }

    @Override
    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {
        return entryServicePortOut.deleteEntry(driveUser, entryId);
    }
}
