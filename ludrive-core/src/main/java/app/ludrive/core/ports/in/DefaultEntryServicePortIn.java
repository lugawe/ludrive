package app.ludrive.core.ports.in;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.validation.Validator;

public final class DefaultEntryServicePortIn implements EntryServicePortIn {

    private final Logger logger;
    private final AuthService authService;
    private final Validator validator;
    private final EventManager eventManager;
    private final EntryServicePortOut entryServicePortOut;

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
    public Entry createEntry(DriveUser driveUser, Entry entry) {

        authService.checkAccess(driveUser);
        validator.validateEntry(entry);

        Entry result = entryServicePortOut.createEntry(driveUser, entry);

        eventManager.triggerEntryCreated(new Events.EntryCreatedProps(driveUser, result));

        return result;
    }

    @Override
    public Stream<Entry> getEntries(DriveUser driveUser) {

        authService.checkAccess(driveUser);

        Consumer<Entry> onEntryRead =
                entry -> eventManager.triggerEntryRead(new Events.EntryReadProps(driveUser, entry));

        return entryServicePortOut.getEntries(driveUser).peek(onEntryRead);
    }

    @Override
    public Entry getEntry(DriveUser driveUser, UUID entryId) {

        authService.checkEntryAccess(driveUser, entryId);

        Entry result = entryServicePortOut.getEntry(driveUser, entryId);

        eventManager.triggerEntryRead(new Events.EntryReadProps(driveUser, result));

        return result;
    }

    @Override
    public Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry) {

        authService.checkEntryAccess(driveUser, entryId);
        validator.validateEntry(updatedEntry);

        Entry oldEntry = entryServicePortOut.getEntry(driveUser, entryId);
        Entry newEntry = entryServicePortOut.updateEntry(driveUser, entryId, updatedEntry);

        eventManager.triggerEntryUpdated(new Events.EntryUpdatedProps(driveUser, oldEntry, newEntry));

        return newEntry;
    }

    @Override
    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {

        authService.checkEntryAccess(driveUser, entryId);

        Entry result = entryServicePortOut.deleteEntry(driveUser, entryId);

        eventManager.triggerEntryDeleted(new Events.EntryDeletedProps(driveUser, result));

        return result;
    }
}
