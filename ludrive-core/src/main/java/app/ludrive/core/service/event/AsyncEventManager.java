package app.ludrive.core.service.event;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import app.ludrive.core.logging.Logger;

public class AsyncEventManager implements EventManager {

    protected final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    protected final Logger logger;
    protected final Collection<? extends EventManager> eventManagers;

    public AsyncEventManager(Logger logger, Collection<? extends EventManager> eventManagers) {
        this.logger = Objects.requireNonNull(logger);
        this.eventManagers = Collections.unmodifiableCollection(eventManagers);
    }

    public AsyncEventManager(Logger logger, EventManager eventManager) {
        this(logger, Collections.singleton(eventManager));
    }

    protected void runAsync(Consumer<EventManager> consumer) {
        logger.debug("runAsync on {} event managers", eventManagers.size());
        for (EventManager eventManager : eventManagers) {
            CompletableFuture.runAsync(() -> consumer.accept(eventManager), executorService)
                    .thenRun(() -> logger.debug("runAsync {} finished", eventManager));
        }
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {
        logger.info("onDriveUserCreated {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserCreated(props));
    }

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {
        logger.info("onDriveUserRead {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserRead(props));
    }

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        logger.info("onDriveUserUpdated {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserUpdated(props));
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {
        logger.info("onDriveUserDeleted {}", props);
        runAsync((eventManager) -> eventManager.onDriveUserDeleted(props));
    }

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {
        logger.info("onEntryCreated {}", props);
        runAsync((eventManager) -> eventManager.onEntryCreated(props));
    }

    @Override
    public void onEntryRead(Events.EntryReadProps props) {
        logger.info("onEntryRead {}", props);
        runAsync((eventManager) -> eventManager.onEntryRead(props));
    }

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {
        logger.info("onEntryUpdated {}", props);
        runAsync((eventManager) -> eventManager.onEntryUpdated(props));
    }

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {
        logger.info("onEntryDeleted {}", props);
        runAsync((eventManager) -> eventManager.onEntryDeleted(props));
    }

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {
        logger.info("onDirectoryCreated {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryCreated(props));
    }

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {
        logger.info("onDirectoryRead {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryRead(props));
    }

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {
        logger.info("onDirectoryUpdated {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryUpdated(props));
    }

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {
        logger.info("onDirectoryDeleted {}", props);
        runAsync((eventManager) -> eventManager.onDirectoryDeleted(props));
    }

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {
        logger.info("onFileCreated {}", props);
        runAsync((eventManager) -> eventManager.onFileCreated(props));
    }

    @Override
    public void onFileRead(Events.FileReadProps props) {
        logger.info("onFileRead {}", props);
        runAsync((eventManager) -> eventManager.onFileRead(props));
    }

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {
        logger.info("onFileUpdated {}", props);
        runAsync((eventManager) -> eventManager.onFileUpdated(props));
    }

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {
        logger.info("onFileDeleted {}", props);
        runAsync((eventManager) -> eventManager.onFileDeleted(props));
    }
}
