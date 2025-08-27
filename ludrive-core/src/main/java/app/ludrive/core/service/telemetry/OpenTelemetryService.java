package app.ludrive.core.service.telemetry;

import app.ludrive.core.domain.management.auth.AuthIdentity;

public class OpenTelemetryService implements TelemetryService {

    public OpenTelemetryService() {}

    @Override
    public void countSuccess(AuthIdentity identity) {}
}
