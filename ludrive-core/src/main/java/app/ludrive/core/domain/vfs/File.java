package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class File extends EntryItem implements Serializable {

    public File(String path) {
        super(path);
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_FILE;
    }
}
