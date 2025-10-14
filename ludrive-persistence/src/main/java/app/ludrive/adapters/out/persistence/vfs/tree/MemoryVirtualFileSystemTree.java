package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Map;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

// TODO
public class MemoryVirtualFileSystemTree implements VirtualFileSystemTree {

    private final Map<String, EntryItem> map = new ConcurrentHashMap<>();

    public MemoryVirtualFileSystemTree() {}

    private Predicate<EntryItem> matches(String path) {
        return (v) -> v.getPath().startsWith(path);
    }

    @Override
    public void put(String path, EntryItem entryItem) {

        if (path == null) {
            throw new NullPointerException("path");
        }
        if (entryItem == null) {
            throw new NullPointerException("entryItem");
        }

        if (!path.equals(entryItem.getPath())) {
            throw new IllegalArgumentException("path and entry item path must match");
        }

        map.put(path, entryItem);
    }

    @Override
    public Optional<? extends EntryItem> get(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return Optional.ofNullable(map.get(path));
    }

    @Override
    public Optional<Directory> getDirectory(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return get(path).filter(v -> v instanceof Directory).map(v -> (Directory) v);
    }

    @Override
    public Optional<File> getFile(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return get(path).filter(v -> v instanceof File).map(v -> (File) v);
    }

    @Override
    public SequencedCollection<? extends EntryItem> list(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return map.values().stream().filter(matches(path)).toList();
    }

    @Override
    public SequencedCollection<Directory> listDirectories(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return map.values().stream()
                .filter(matches(path))
                .filter(v -> v instanceof Directory)
                .map(v -> (Directory) v)
                .toList();
    }

    @Override
    public SequencedCollection<File> listFiles(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return map.values().stream()
                .filter(matches(path))
                .filter(v -> v instanceof File)
                .map(v -> (File) v)
                .toList();
    }

    @Override
    public void remove(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        map.remove(path);
    }
}
