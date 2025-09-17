package app.ludrive.server.cdi.core;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.in.*;
import app.ludrive.core.ports.out.*;
import app.ludrive.core.ports.out.repository.DirectoryRepository;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.core.ports.out.repository.EntryRepository;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.auth.DefaultAuthService;
import app.ludrive.core.service.event.AsyncEventManager;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.LoggingEventManager;
import app.ludrive.core.service.telemetry.TelemetryEventManager;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.core.service.vfs.VirtualFSService;
import app.ludrive.server.cdi.core.logging.Slf4jLoggerFactory;
import app.ludrive.server.otel.OpenTelemetryService;

import io.opentelemetry.api.metrics.Meter;

@ApplicationScoped
public class ProducerFactory {

    @Inject
    private Instance<Meter> meter;

    public ProducerFactory() {}

    @Produces
    public AuthService produceAuthService() {
        return new DefaultAuthService();
    }

    @Produces
    public Validator produceValidator() {
        return new Validator();
    }

    @Produces
    public EventManager produceEventManager() {

        Logger logger1 = Slf4jLoggerFactory.getLogger(AsyncEventManager.class);
        Logger logger2 = Slf4jLoggerFactory.getLogger(LoggingEventManager.class);

        List<EventManager> eventManagers = new ArrayList<>();

        LoggingEventManager loggingEventManager = new LoggingEventManager(logger2);
        eventManagers.add(loggingEventManager);

        if (meter.isResolvable()) {
            TelemetryEventManager telemetryEventManager =
                    new TelemetryEventManager(new OpenTelemetryService(meter.get()));
            eventManagers.add(telemetryEventManager);
        }

        return new AsyncEventManager(logger1, eventManagers);
    }

    @Produces
    public DriveUserServicePortIn produceDriveUserServicePortIn(
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            DriveUserServicePortOut driveUserServicePortOut) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultDriveUserServicePortIn.class);

        return new DefaultDriveUserServicePortIn(logger, authService, validator, eventManager, driveUserServicePortOut);
    }

    @Produces
    public EntryServicePortIn produceEntryServicePortIn(
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            EntryServicePortOut entryServicePortOut) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultEntryServicePortIn.class);

        return new DefaultEntryServicePortIn(logger, authService, validator, eventManager, entryServicePortOut);
    }

    @Produces
    public DirectoryServicePortIn produceDirectoryServicePortIn(
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            DirectoryServicePortOut directoryServicePortOut) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultDirectoryServicePortIn.class);

        return new DefaultDirectoryServicePortIn(logger, authService, validator, eventManager, directoryServicePortOut);
    }

    @Produces
    public FileServicePortIn produceFileServicePortIn(
            AuthService authService,
            Validator validator,
            EventManager eventManager,
            FileServicePortOut fileServicePortOut) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultFileServicePortIn.class);

        return new DefaultFileServicePortIn(logger, authService, validator, eventManager, fileServicePortOut);
    }

    @Produces
    public DriveUserServicePortOut produceDriveUserServicePortOut(DriveUserRepository driveUserRepository) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultDriveUserServicePortOut.class);

        return new DefaultDriveUserServicePortOut(logger, driveUserRepository);
    }

    @Produces
    public EntryServicePortOut produceEntryServicePortOut(EntryRepository entryRepository) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultEntryServicePortOut.class);

        return new DefaultEntryServicePortOut(logger, entryRepository);
    }

    @Produces
    public DirectoryServicePortOut produceDirectoryServicePortOut(
            DirectoryRepository directoryRepository, VirtualFSService virtualFSService) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultDirectoryServicePortOut.class);

        return new DefaultDirectoryServicePortOut(logger, directoryRepository, virtualFSService);
    }

    @Produces
    public FileServicePortOut produceFileServicePortOut(
            FileRepository fileRepository, VirtualFSService virtualFSService) {

        Logger logger = Slf4jLoggerFactory.getLogger(DefaultFileServicePortOut.class);

        return new DefaultFileServicePortOut(logger, fileRepository, virtualFSService);
    }
}
