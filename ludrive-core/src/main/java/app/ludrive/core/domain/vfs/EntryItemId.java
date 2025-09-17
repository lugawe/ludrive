package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.UUID;

public record EntryItemId(UUID entryId, String path) implements Serializable {}
