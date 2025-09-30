package app.ludrive.core.ports.out.repository;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;

public interface EntryRepository {

    Entry createEntry(AuthIdentity identity, Entry entry);

    Stream<Entry> getEntries(AuthIdentity identity);

    Entry getEntry(AuthIdentity identity, UUID entryId);

    Entry updateEntry(AuthIdentity identity, UUID entryId, Entry entry);

    Entry deleteEntry(AuthIdentity identity, UUID entryId);
}
