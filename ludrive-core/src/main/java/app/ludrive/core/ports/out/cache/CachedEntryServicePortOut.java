package app.ludrive.core.ports.out.cache;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.event.AbstractEventListener;
import app.ludrive.core.service.event.Events;

public final class CachedEntryServicePortOut implements EntryServicePortOut, AbstractEventListener {

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

        Supplier<Entry> loader = () -> entryServicePortOut.getEntry(driveUser, entryId);

        return entryServiceCache.computeIfAbsent(entryId, loader);
    }

    @Override
    public Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry) {
        return entryServicePortOut.updateEntry(driveUser, entryId, updatedEntry);
    }

    @Override
    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {
        return entryServicePortOut.deleteEntry(driveUser, entryId);
    }

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {

        List<UUID> keys = props.updatedEntries().stream()
                .flatMap(e -> Stream.of(e.oldValue().getId(), e.newValue().getId()))
                .distinct()
                .toList();

        entryServiceCache.evict(keys);
    }

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {

        List<UUID> keys =
                props.deletedEntries().stream().map(e -> e.value().getId()).toList();

        entryServiceCache.evict(keys);
    }
}
