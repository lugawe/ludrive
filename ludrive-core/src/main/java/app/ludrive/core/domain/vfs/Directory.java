package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.UUID;

public class Directory extends EntryItem implements Serializable {

    public static final String ROOT = "/";

    public Directory() {}

    public Directory(UUID entryId, String path) {
        super(new EntryItemId(entryId, path));
    }
}
