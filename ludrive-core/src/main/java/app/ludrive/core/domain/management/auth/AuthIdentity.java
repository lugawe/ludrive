package app.ludrive.core.domain.management.auth;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

public interface AuthIdentity extends Principal {

    UUID getId();

    @Override
    String getName();

    Set<Roles.Role> getRoles();
}
