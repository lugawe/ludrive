package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class File extends EntryItem implements Serializable {

    public File(EntryItemId entryItemId) {
        super(entryItemId);
    }

    public File(String path) {
        this(new EntryItemId(path));
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_FILE;
    }
}
