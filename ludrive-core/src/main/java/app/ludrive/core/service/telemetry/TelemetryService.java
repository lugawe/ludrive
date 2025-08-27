package app.ludrive.core.service.telemetry;

import app.ludrive.core.domain.management.auth.AuthIdentity;

public interface TelemetryService {

    void countSuccess(AuthIdentity identity);
}
