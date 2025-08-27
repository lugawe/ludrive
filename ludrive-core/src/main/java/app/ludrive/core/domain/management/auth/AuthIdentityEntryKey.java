package app.ludrive.core.domain.management.auth;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class AuthIdentityEntryKey implements Serializable {

    private UUID authIdentityId;
    private UUID entryId;

    public AuthIdentityEntryKey() {}

    public AuthIdentityEntryKey(UUID authIdentityId, UUID entryId) {
        this.authIdentityId = authIdentityId;
        this.entryId = entryId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthIdentityEntryKey that)) return false;
        return Objects.equals(getAuthIdentityId(), that.getAuthIdentityId())
                && Objects.equals(getEntryId(), that.getEntryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthIdentityId(), getEntryId());
    }

    public UUID getAuthIdentityId() {
        return authIdentityId;
    }

    public void setAuthIdentityId(UUID authIdentityId) {
        this.authIdentityId = authIdentityId;
    }

    public UUID getEntryId() {
        return entryId;
    }

    public void setEntryId(UUID entryId) {
        this.entryId = entryId;
    }
}
