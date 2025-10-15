package app.ludrive.adapters.in.api.rest.service;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.JsonEntry;
import app.ludrive.adapters.in.api.rest.json.converter.JsonConverter;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.in.EntryServicePortIn;

@ApplicationScoped
public class RestEntryService {

    private final JsonConverter jsonConverter;
    private final EntryServicePortIn entryServicePortIn;

    @Inject
    public RestEntryService(JsonConverter jsonConverter, EntryServicePortIn entryServicePortIn) {
        this.jsonConverter = jsonConverter;
        this.entryServicePortIn = entryServicePortIn;
    }

    public JsonEntry createEntry(DriveUser driveUser, JsonEntry jsonEntry) {

        Entry entry = jsonConverter.toEntry(jsonEntry);

        Entry result = entryServicePortIn.createEntry(driveUser, entry);

        return jsonConverter.toJsonEntry(result);
    }

    public Stream<JsonEntry> getEntries(DriveUser driveUser) {

        Stream<Entry> entries = entryServicePortIn.getEntries(driveUser);

        return entries.map(jsonConverter::toJsonEntry);
    }

    public JsonEntry getEntry(DriveUser driveUser, UUID entryId) {

        Entry entry = entryServicePortIn.getEntry(driveUser, entryId);

        return jsonConverter.toJsonEntry(entry);
    }

    public JsonEntry updateEntry(DriveUser driveUser, UUID entryId, JsonEntry jsonEntry) {

        Entry entry = jsonConverter.toEntry(jsonEntry);

        Entry result = entryServicePortIn.updateEntry(driveUser, entryId, entry);

        return jsonConverter.toJsonEntry(result);
    }

    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {

        return entryServicePortIn.deleteEntry(driveUser, entryId);
    }
}
