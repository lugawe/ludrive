package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.Objects;

public record EntryItemId(String path) implements Serializable {

    public EntryItemId(String path) {
        this.path = Objects.requireNonNull(path);
    }
}
