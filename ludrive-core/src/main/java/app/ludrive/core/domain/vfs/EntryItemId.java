package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record EntryItemId(UUID entryId, String path) implements Serializable {

    public EntryItemId(UUID entryId, String path) {
        this.entryId = Objects.requireNonNull(entryId, "entryId");
        this.path = Objects.requireNonNull(path, "path");
    }
}
