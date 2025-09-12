package app.ludrive.server.otel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.service.telemetry.TelemetryService;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;

@ApplicationScoped
public class OpenTelemetryService implements TelemetryService {

    public static final String SUCCESSFUL_REQUESTS = "successful-requests";

    protected final LongCounter successCounter;

    @Inject
    public OpenTelemetryService(Meter meter) {
        this.successCounter = meter.counterBuilder(SUCCESSFUL_REQUESTS).build();
    }

    @Override
    public void countSuccess(AuthIdentity identity) {

        Attributes attributes = Attributes.builder()
                .put("auth_identity", String.format("%s : %s", identity.getId(), identity.getName()))
                .build();

        successCounter.add(1, attributes);
    }
}
