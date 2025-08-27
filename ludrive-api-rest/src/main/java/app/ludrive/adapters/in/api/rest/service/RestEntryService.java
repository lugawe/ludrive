package app.ludrive.adapters.in.api.rest.service;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.converter.JsonConverter;
import app.ludrive.adapters.in.api.rest.json.JsonEntry;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
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

    public JsonEntry createEntry(AuthIdentity identity, JsonEntry jsonEntry) {

        Entry entry = jsonConverter.toEntry(jsonEntry);

        Entry result = entryServicePortIn.createEntry(identity, entry);

        return jsonConverter.toJsonEntry(result);
    }

    public Stream<JsonEntry> getEntries(AuthIdentity identity) {

        Stream<Entry> entries = entryServicePortIn.getEntries(identity);

        return entries.map(jsonConverter::toJsonEntry);
    }

    public JsonEntry getEntry(AuthIdentity identity, UUID entryId) {

        Entry entry = entryServicePortIn.getEntry(identity, entryId);

        return jsonConverter.toJsonEntry(entry);
    }

    public JsonEntry updateEntry(AuthIdentity identity, UUID entryId, JsonEntry jsonEntry) {

        Entry entry = jsonConverter.toEntry(jsonEntry);

        Entry result = entryServicePortIn.updateEntry(identity, entryId, entry);

        return jsonConverter.toJsonEntry(result);
    }

    public UUID deleteEntry(AuthIdentity identity, UUID entryId) {

        return entryServicePortIn.deleteEntry(identity, entryId);
    }
}
