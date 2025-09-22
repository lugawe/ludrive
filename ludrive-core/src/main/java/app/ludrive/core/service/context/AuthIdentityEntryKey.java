package app.ludrive.core.service.context;

import java.io.Serializable;
import java.util.UUID;

public record AuthIdentityEntryKey(UUID authIdentityId, UUID entryId) implements Serializable {}
