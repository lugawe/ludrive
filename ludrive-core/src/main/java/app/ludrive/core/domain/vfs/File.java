package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.UUID;

public class File extends EntryItem implements Serializable {

    public File() {}

    public File(UUID entryId, String path) {
        super(new EntryItemId(entryId, path));
    }
}
