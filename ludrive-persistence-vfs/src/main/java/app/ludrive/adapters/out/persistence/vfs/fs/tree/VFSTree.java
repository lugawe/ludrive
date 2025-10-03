package app.ludrive.adapters.out.persistence.vfs.fs.tree;

import java.util.SequencedCollection;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;

public interface VFSTree {

    void set(String path, EntryItem entryItem);

    EntryItem get(String path);

    default Directory getDirectory(String path) {
        EntryItem result = get(path);
        if (result instanceof Directory directory) {
            return directory;
        }
        throw new VFSException(String.format("%s is not an directory", path));
    }

    default File getFile(String path) {
        EntryItem result = get(path);
        if (result instanceof File file) {
            return file;
        }
        throw new VFSException(String.format("%s is not an file", path));
    }

    SequencedCollection<? extends EntryItem> getChildren(String path);

    void remove(String path);
}
