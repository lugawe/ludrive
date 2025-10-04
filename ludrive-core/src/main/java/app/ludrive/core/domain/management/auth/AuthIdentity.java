package app.ludrive.core.domain.management.auth;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

import app.ludrive.core.domain.Identifiable;

public sealed interface AuthIdentity extends Identifiable, Principal permits DriveUser, AnonymousAuthIdentity {

    @Override
    UUID getId();

    @Override
    String getName();

    Set<Roles.Role> getRoles();
}
