package app.ludrive.adapters.in.api.rest.auth;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.ports.out.repository.DriveUserRepository;

import org.eclipse.microprofile.jwt.JsonWebToken;

// TODO Rewrite in modular and cached strategy
@ApplicationScoped
public class AuthIdentityProvider implements Provider<AuthIdentity> {

    private final DriveUserRepository driveUserRepository;
    private final JsonWebToken jsonWebToken;

    @Inject
    public AuthIdentityProvider(DriveUserRepository driveUserRepository, JsonWebToken jsonWebToken) {
        this.driveUserRepository = driveUserRepository;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public AuthIdentity get() {
        UUID driveUserId = UUID.fromString(jsonWebToken.getClaim(Jwts.IDENTITY_ID_CLAIM));
        return driveUserRepository.getDriveUser(driveUserId);
    }
}
