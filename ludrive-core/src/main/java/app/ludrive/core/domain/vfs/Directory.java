package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class Directory extends EntryItem implements Serializable {

    public static final String ROOT = "/";

    public Directory(EntryItemId entryItemId) {
        super(entryItemId);
    }

    public Directory(String path) {
        this(new EntryItemId(path));
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_DIRECTORY;
    }
}
