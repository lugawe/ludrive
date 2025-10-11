package app.ludrive.core.domain.vfs;

import java.util.UUID;

public final class Directory extends EntryItem {

    public static final String ROOT = "/";

    public Directory(EntryItemId entryItemId) {
        super(entryItemId);
    }

    public Directory(UUID entryId, String path) {
        this(new EntryItemId(entryId, path));
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_DIRECTORY;
    }
}
