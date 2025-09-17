package app.ludrive.core.domain.management.auth;

import java.io.Serializable;
import java.util.UUID;

public record AuthIdentityEntryKey(UUID authIdentityId, UUID entryId) implements Serializable {}
