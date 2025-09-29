package app.ludrive.core.ports.in;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public class DefaultEntryServicePortIn implements EntryServicePortIn {

    protected final Logger logger;
    protected final AuthService authService;
    protected final Validator validator;
    protected final EventManager eventManager;
    protected final EntryServicePortOut entryServicePortOut;

    public DefaultEntryServicePortIn(
            Logger logger,
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            EntryServicePortOut entryServicePortOut) {
        this.logger = logger;
        this.authService = authService;
        this.validator = validator;
        this.eventManager = eventManager;
        this.entryServicePortOut = entryServicePortOut;
    }

    @Override
    public Entry createEntry(AuthIdentity identity, Entry entry) {

        authService.checkAccess(identity);
        validator.validateEntry(entry);

        Entry result = entryServicePortOut.createEntry(identity, entry);

        eventManager.onEntryCreated(new Events.EntryCreatedProps(identity, result.getId()));

        return result;
    }

    @Override
    public Stream<Entry> getEntries(AuthIdentity identity) {

        authService.checkAccess(identity);

        Consumer<Entry> onEntryRead = entry -> {
            UUID id = entry.getId();
            eventManager.onEntryRead(new Events.EntryReadProps(identity, id));
        };

        return entryServicePortOut.getEntries(identity).peek(onEntryRead);
    }

    @Override
    public Entry getEntry(AuthIdentity identity, UUID entryId) {

        authService.checkEntryAccess(identity, entryId);

        Entry result = entryServicePortOut.getEntry(identity, entryId);

        eventManager.onEntryRead(new Events.EntryReadProps(identity, result.getId()));

        return result;
    }

    @Override
    public Entry updateEntry(AuthIdentity identity, UUID entryId, Entry entry) {

        authService.checkEntryAccess(identity, entryId);

        Entry result = entryServicePortOut.updateEntry(identity, entryId, entry);

        eventManager.onEntryUpdated(new Events.EntryUpdatedProps(identity, result.getId()));

        return result;
    }

    @Override
    public UUID deleteEntry(AuthIdentity identity, UUID entryId) {

        authService.checkEntryAccess(identity, entryId);

        UUID result = entryServicePortOut.deleteEntry(identity, entryId);

        eventManager.onEntryDeleted(new Events.EntryDeletedProps(identity, result));

        return result;
    }
}
