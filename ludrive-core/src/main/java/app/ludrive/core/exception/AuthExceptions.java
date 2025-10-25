package app.ludrive.core.exception;

import app.ludrive.core.domain.management.auth.AuthIdentity;

public final class AuthExceptions {

    private AuthExceptions() {}

    public static AccessDeniedException createNoAuthIdentityProvided() {
        return new AccessDeniedException("No identity provided");
    }

    public static AccessDeniedException createNoAccess(AuthIdentity identity) {
        return new AccessDeniedException("No access: " + identity.getName());
    }
}
