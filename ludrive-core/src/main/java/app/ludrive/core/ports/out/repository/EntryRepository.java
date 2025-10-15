package app.ludrive.core.ports.out.repository;

import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;

public interface EntryRepository {

    Entry createEntry(DriveUser driveUser, Entry entry);

    Stream<Entry> getEntries(DriveUser driveUser);

    Entry getEntry(DriveUser driveUser, UUID entryId);

    Entry updateEntry(DriveUser driveUser, UUID entryId, Entry entry);

    Entry deleteEntry(DriveUser driveUser, UUID entryId);
}
