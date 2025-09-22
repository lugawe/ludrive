package app.ludrive.core.service.context;

import java.util.UUID;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;

public interface ContextService {

    AuthIdentity getAuthIdentity();

    Entry getEntry();

    default AuthIdentityEntryKey getKey() {
        UUID authIdentityId = getAuthIdentity().getId();
        UUID entryId = getEntry().getId();
        return new AuthIdentityEntryKey(authIdentityId, entryId);
    }
}
