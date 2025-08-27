package app.ludrive.adapters.in.api.rest.auth;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import app.ludrive.core.domain.management.DriveUser;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.ports.out.repository.DriveUserRepository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.eclipse.microprofile.jwt.JsonWebToken;

// TODO
@ApplicationScoped
public class AuthIdentityProvider implements Provider<AuthIdentity> {

    private final LoadingCache<UUID, DriveUser> userLoadingCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<>() {
                @Override
                public DriveUser load(UUID driveUserId) throws Exception {
                    return driveUserRepository.getDriveUser(driveUserId);
                }
            });

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
        return userLoadingCache.getUnchecked(driveUserId);
    }
}
