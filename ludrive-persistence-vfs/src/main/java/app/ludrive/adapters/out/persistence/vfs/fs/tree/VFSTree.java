package app.ludrive.adapters.out.persistence.vfs.fs.tree;

import java.util.stream.Stream;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;

public interface VFSTree {

    void put(String path, EntryItem entryItem);

    EntryItem get(String path);

    default Directory getDirectory(String path) {
        EntryItem result = get(path);
        if (result instanceof Directory directory) {
            return directory;
        }
        throw new VFSException(String.format("%s is not a directory", path));
    }

    default File getFile(String path) {
        EntryItem result = get(path);
        if (result instanceof File file) {
            return file;
        }
        throw new VFSException(String.format("%s is not a file", path));
    }

    Stream<? extends EntryItem> list(String path);

    default Stream<Directory> listDirectories(String path) {
        return list(path).filter(e -> e instanceof Directory).map(e -> (Directory) e);
    }

    default Stream<File> listFiles(String path) {
        return list(path).filter(e -> e instanceof File).map(e -> (File) e);
    }

    void remove(String path);
}
