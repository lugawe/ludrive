package app.ludrive.core.service.auth;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.exception.AccessDeniedException;

public class DefaultAuthService implements AuthService {

    public DefaultAuthService() {}

    @Override
    public void checkAccess(AuthIdentity identity) throws AccessDeniedException {
        if (identity == null) {
            throw new AccessDeniedException("no auth identity provided");
        }
    }

    @Override
    public void checkDriveUserAccess(AuthIdentity identity, UUID driveUserId) throws AccessDeniedException {
        checkAccess(identity);
        if (!identity.getId().equals(driveUserId)) {
            throw new AccessDeniedException("cannot access drive user");
        }
    }

    @Override
    public void checkEntryAccess(AuthIdentity identity, UUID entryId) throws AccessDeniedException {
        checkAccess(identity);
    }
}
