package app.ludrive.core.service.auth;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.exception.AccessDeniedException;

public interface AuthService {

    void checkAccess(AuthIdentity identity) throws AccessDeniedException;

    void checkDriveUserAccess(AuthIdentity identity, UUID driveUserId) throws AccessDeniedException;

    void checkEntryAccess(AuthIdentity identity, UUID entryId) throws AccessDeniedException;
}
