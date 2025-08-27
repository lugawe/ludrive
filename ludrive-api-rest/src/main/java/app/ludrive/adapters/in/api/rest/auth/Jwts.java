package app.ludrive.adapters.in.api.rest.auth;

import java.time.Duration;
import java.util.Set;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

import io.smallrye.jwt.build.Jwt;

// TODO
public final class Jwts {

    public static final String IDENTITY_ID_CLAIM = "identity_id";
    public static final String IDENTITY_NAME_CLAIM = "identity_name";

    private Jwts() {}

    public static String create(AuthIdentity identity) {
        String id = identity.getId().toString();
        String name = identity.getName();
        return Jwt.upn(id)
                .groups(Set.of(Roles.ROLE_DRIVE_ADMIN, Roles.ROLE_DRIVE_USER))
                .claim(IDENTITY_ID_CLAIM, id)
                .claim(IDENTITY_NAME_CLAIM, name)
                .expiresIn(Duration.ofHours(8))
                .sign();
    }
}
