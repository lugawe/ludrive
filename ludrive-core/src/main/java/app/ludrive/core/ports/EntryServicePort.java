package app.ludrive.core.ports;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;

public interface EntryServicePort {

    Entry createEntry(DriveUser driveUser, Entry entry);

    Stream<Entry> getEntries(DriveUser driveUser);

    Entry getEntry(DriveUser driveUser, UUID entryId);

    Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry);

    Entry deleteEntry(DriveUser driveUser, UUID entryId);
}
