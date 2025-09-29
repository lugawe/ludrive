package app.ludrive.server.cdi.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.service.event.AsyncEventManager;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.LoggingEventManager;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.telemetry.TelemetryEventManager;
import app.ludrive.server.cdi.util.ClassNamed;
import app.ludrive.server.otel.OpenTelemetryService;

import io.opentelemetry.api.metrics.Meter;

@ApplicationScoped
public class EventManagerProducer {

    @Inject
    @ClassNamed(EventManager.class)
    private Logger logger;

    @Inject
    @ClassNamed(LoggingEventManager.class)
    private Logger logger2;

    @Inject
    private Instance<Meter> meter;

    public EventManagerProducer() {}

    private Collection<? extends EventManager> eventManagers() {

        List<EventManager> result = new ArrayList<>();

        LoggingEventManager loggingEventManager = new LoggingEventManager(logger2);
        result.add(loggingEventManager);

        if (meter.isResolvable()) {
            TelemetryEventManager telemetryEventManager =
                    new TelemetryEventManager(new OpenTelemetryService(meter.get()));
            result.add(telemetryEventManager);
        }

        return result;
    }

    @Produces
    public EventManager produce() {

        return new AsyncEventManager(logger, eventManagers());
    }
}
