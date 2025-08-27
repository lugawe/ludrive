package app.ludrive.core.ports;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;

public interface EntryServicePort {

    Entry createEntry(AuthIdentity identity, Entry entry);

    Stream<Entry> getEntries(AuthIdentity identity);

    Entry getEntry(AuthIdentity identity, UUID entryId);

    Entry updateEntry(AuthIdentity identity, UUID entryId, Entry entry);

    UUID deleteEntry(AuthIdentity identity, UUID entryId);
}
