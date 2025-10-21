package app.ludrive.server.cdi.core;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.service.event.AsyncEventManager;
import app.ludrive.core.service.event.EventListener;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.logging.LoggingEventListener;
import app.ludrive.core.service.telemetry.TelemetryEventListener;
import app.ludrive.server.cdi.util.ClassNamed;
import app.ludrive.server.otel.OpenTelemetryService;

import io.opentelemetry.api.metrics.Meter;

@ApplicationScoped
public class EventManagerProducer {

    @Inject
    @ClassNamed(EventManager.class)
    private Logger logger;

    @Inject
    @ClassNamed(LoggingEventListener.class)
    private Logger logger2;

    @Inject
    private Instance<EventListener> eventListeners;

    @Inject
    private Instance<Meter> meter;

    public EventManagerProducer() {}

    private List<EventListener> createEventListeners() {

        List<EventListener> result = new ArrayList<>();

        LoggingEventListener loggingEventListener = new LoggingEventListener(logger2);
        result.add(loggingEventListener);

        for (EventListener eventListener : eventListeners) {
            result.add(eventListener);
        }

        if (meter.isResolvable()) {
            TelemetryEventListener telemetryEventListener =
                    new TelemetryEventListener(new OpenTelemetryService(meter.get()));
            result.add(telemetryEventListener);
        }

        return result;
    }

    @Produces
    public EventManager produce() {

        return new AsyncEventManager(logger, createEventListeners());
    }
}
