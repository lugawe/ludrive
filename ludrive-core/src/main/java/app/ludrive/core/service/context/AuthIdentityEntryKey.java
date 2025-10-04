package app.ludrive.core.service.context;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record AuthIdentityEntryKey(UUID authIdentityId, UUID entryId) implements Serializable {

    private static final String DELIMITER = ":";

    public AuthIdentityEntryKey(UUID authIdentityId, UUID entryId) {
        this.authIdentityId = Objects.requireNonNull(authIdentityId);
        this.entryId = Objects.requireNonNull(entryId);
    }

    public String getKeyString() {
        return authIdentityId + DELIMITER + entryId;
    }

    public static AuthIdentityEntryKey fromKeyString(String keyString) {

        if (keyString == null) {
            throw new NullPointerException("keyString");
        }

        String[] parts = keyString.split(DELIMITER);
        if (parts.length != 2) {
            throw new IllegalArgumentException("keyString");
        }

        UUID authIdentityId = UUID.fromString(parts[0]);
        UUID entryId = UUID.fromString(parts[1]);

        return new AuthIdentityEntryKey(authIdentityId, entryId);
    }
}
