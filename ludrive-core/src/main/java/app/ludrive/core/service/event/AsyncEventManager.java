package app.ludrive.core.service.event;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import app.ludrive.core.service.logging.Logger;

public class AsyncEventManager implements EventManager {

    protected final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    protected final Logger logger;
    protected final SequencedCollection<? extends EventListener> eventListeners;

    public AsyncEventManager(Logger logger, SequencedCollection<? extends EventListener> eventListeners) {
        this.logger = Objects.requireNonNull(logger);
        this.eventListeners = Collections.unmodifiableSequencedCollection(eventListeners);
    }

    public AsyncEventManager(Logger logger, EventListener... eventListeners) {
        this(logger, Arrays.asList(eventListeners));
    }

    public AsyncEventManager(Logger logger, EventListener eventListener) {
        this(logger, Collections.singletonList(eventListener));
    }

    protected void runAsync(Consumer<EventListener> consumer) {
        logger.debug("runAsync on {} event managers", eventListeners.size());
        for (EventListener eventListener : eventListeners) {
            CompletableFuture.runAsync(() -> consumer.accept(eventListener), executorService)
                    .thenRun(() -> logger.debug("runAsync on {} finished", eventListener));
        }
    }

    @Override
    public void triggerDriveUserCreated(Events.DriveUserCreatedProps props) {
        logger.debug("onDriveUserCreated {}", props);
        runAsync((eventListener) -> eventListener.onDriveUserCreated(props));
    }

    @Override
    public void triggerDriveUserRead(Events.DriveUserReadProps props) {
        logger.debug("onDriveUserRead {}", props);
        runAsync((eventListener) -> eventListener.onDriveUserRead(props));
    }

    @Override
    public void triggerDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        logger.debug("onDriveUserUpdated {}", props);
        runAsync((eventListener) -> eventListener.onDriveUserUpdated(props));
    }

    @Override
    public void triggerDriveUserDeleted(Events.DriveUserDeletedProps props) {
        logger.debug("onDriveUserDeleted {}", props);
        runAsync((eventListener) -> eventListener.onDriveUserDeleted(props));
    }

    @Override
    public void triggerEntryCreated(Events.EntryCreatedProps props) {
        logger.debug("onEntryCreated {}", props);
        runAsync((eventListener) -> eventListener.onEntryCreated(props));
    }

    @Override
    public void triggerEntryRead(Events.EntryReadProps props) {
        logger.debug("onEntryRead {}", props);
        runAsync((eventListener) -> eventListener.onEntryRead(props));
    }

    @Override
    public void triggerEntryUpdated(Events.EntryUpdatedProps props) {
        logger.debug("onEntryUpdated {}", props);
        runAsync((eventListener) -> eventListener.onEntryUpdated(props));
    }

    @Override
    public void triggerEntryDeleted(Events.EntryDeletedProps props) {
        logger.debug("onEntryDeleted {}", props);
        runAsync((eventListener) -> eventListener.onEntryDeleted(props));
    }

    @Override
    public void triggerDirectoryCreated(Events.DirectoryCreatedProps props) {
        logger.debug("onDirectoryCreated {}", props);
        runAsync((eventListener) -> eventListener.onDirectoryCreated(props));
    }

    @Override
    public void triggerDirectoryRead(Events.DirectoryReadProps props) {
        logger.debug("onDirectoryRead {}", props);
        runAsync((eventListener) -> eventListener.onDirectoryRead(props));
    }

    @Override
    public void triggerDirectoryUpdated(Events.DirectoryUpdatedProps props) {
        logger.debug("onDirectoryUpdated {}", props);
        runAsync((eventListener) -> eventListener.onDirectoryUpdated(props));
    }

    @Override
    public void triggerDirectoryDeleted(Events.DirectoryDeletedProps props) {
        logger.debug("onDirectoryDeleted {}", props);
        runAsync((eventListener) -> eventListener.onDirectoryDeleted(props));
    }

    @Override
    public void triggerFileCreated(Events.FileCreatedProps props) {
        logger.debug("onFileCreated {}", props);
        runAsync((eventListener) -> eventListener.onFileCreated(props));
    }

    @Override
    public void triggerFileRead(Events.FileReadProps props) {
        logger.debug("onFileRead {}", props);
        runAsync((eventListener) -> eventListener.onFileRead(props));
    }

    @Override
    public void triggerFileUpdated(Events.FileUpdatedProps props) {
        logger.debug("onFileUpdated {}", props);
        runAsync((eventListener) -> eventListener.onFileUpdated(props));
    }

    @Override
    public void triggerFileDeleted(Events.FileDeletedProps props) {
        logger.debug("onFileDeleted {}", props);
        runAsync((eventListener) -> eventListener.onFileDeleted(props));
    }
}
