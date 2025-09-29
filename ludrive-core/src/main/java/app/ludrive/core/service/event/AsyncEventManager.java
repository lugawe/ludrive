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
    protected final Collection<? extends EventManager> eventManagers;

    public AsyncEventManager(Logger logger, Collection<? extends EventManager> eventManagers) {
        this.logger = Objects.requireNonNull(logger);
        this.eventManagers = Collections.unmodifiableCollection(eventManagers);
    }

    public AsyncEventManager(Logger logger, EventManager... eventManagers) {
        this(logger, Arrays.asList(eventManagers));
    }

    public AsyncEventManager(Logger logger, EventManager eventManager) {
        this(logger, Collections.singletonList(eventManager));
    }

    protected void runAsync(Consumer<EventManager> consumer) {
        logger.debug("runAsync on {} event managers", eventManagers.size());
        for (EventManager eventManager : eventManagers) {
            CompletableFuture.runAsync(() -> consumer.accept(eventManager), executorService)
                    .thenRun(() -> logger.debug("runAsync on {} finished", eventManager));
        }
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {
        logger.debug("onDriveUserCreated {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserCreated(props));
    }

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {
        logger.debug("onDriveUserRead {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserRead(props));
    }

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        logger.debug("onDriveUserUpdated {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserUpdated(props));
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {
        logger.debug("onDriveUserDeleted {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserDeleted(props));
    }

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {
        logger.debug("onEntryCreated {}", props);
        runAsync((eventManager) -> eventManager.onEntryCreated(props));
    }

    @Override
    public void onEntryRead(Events.EntryReadProps props) {
        logger.debug("onEntryRead {}", props);
        runAsync((eventManager) -> eventManager.onEntryRead(props));
    }

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {
        logger.debug("onEntryUpdated {}", props);
        runAsync((eventManager) -> eventManager.onEntryUpdated(props));
    }

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {
        logger.debug("onEntryDeleted {}", props);
        runAsync((eventManager) -> eventManager.onEntryDeleted(props));
    }

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {
        logger.debug("onDirectoryCreated {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryCreated(props));
    }

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {
        logger.debug("onDirectoryRead {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryRead(props));
    }

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {
        logger.debug("onDirectoryUpdated {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryUpdated(props));
    }

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {
        logger.debug("onDirectoryDeleted {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryDeleted(props));
    }

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {
        logger.debug("onFileCreated {}", props);
        runAsync((eventManager) -> eventManager.onFileCreated(props));
    }

    @Override
    public void onFileRead(Events.FileReadProps props) {
        logger.debug("onFileRead {}", props);
        runAsync((eventManager) -> eventManager.onFileRead(props));
    }

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {
        logger.debug("onFileUpdated {}", props);
        runAsync((eventManager) -> eventManager.onFileUpdated(props));
    }

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {
        logger.debug("onFileDeleted {}", props);
        runAsync((eventManager) -> eventManager.onFileDeleted(props));
    }
}
