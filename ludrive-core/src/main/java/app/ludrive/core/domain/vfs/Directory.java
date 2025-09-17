package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.UUID;

public final class Directory extends EntryItem implements Serializable {

    public static final String ROOT = "/";

    public Directory(EntryItemId entryItemId) {
        super(entryItemId);
    }

    public Directory(UUID entryId, String path) {
        this(new EntryItemId(entryId, path));
    }
}
