package app.ludrive.adapters.in.api.rest.auth;

import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.repository.DriveUserRepository;
import app.ludrive.core.service.context.ContextService;

import org.eclipse.microprofile.jwt.JsonWebToken;

// TODO cached
@RequestScoped
public class RestContextService implements ContextService {

    private final DriveUserRepository driveUserRepository;
    private final JsonWebToken jsonWebToken;

    @Inject
    public RestContextService(DriveUserRepository driveUserRepository, JsonWebToken jsonWebToken) {
        this.driveUserRepository = driveUserRepository;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public DriveUser getAuthIdentity() {
        UUID driveUserId = UUID.fromString(jsonWebToken.getClaim(Jwts.IDENTITY_ID_CLAIM));
        return driveUserRepository.getDriveUser(driveUserId);
    }

    @Override
    public Entry getEntry() {
        throw new UnsupportedOperationException();
    }
}
