package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.UUID;

public final class File extends EntryItem implements Serializable {

    public File(EntryItemId entryItemId) {
        super(entryItemId);
    }

    public File(UUID entryId, String path) {
        this(new EntryItemId(entryId, path));
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_FILE;
    }
}
