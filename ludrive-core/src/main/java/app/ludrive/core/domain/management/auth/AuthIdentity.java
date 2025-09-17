package app.ludrive.core.domain.management.auth;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

public sealed interface AuthIdentity extends Principal permits DriveUser, AnonymousAuthIdentity {

    UUID getId();

    @Override
    String getName();

    Set<Roles.Role> getRoles();
}
