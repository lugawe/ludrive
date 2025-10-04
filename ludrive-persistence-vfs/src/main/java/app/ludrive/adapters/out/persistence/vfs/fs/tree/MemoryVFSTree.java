package app.ludrive.adapters.out.persistence.vfs.fs.tree;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import app.ludrive.core.domain.vfs.EntryItem;

public class MemoryVFSTree implements VFSTree {

    // TODO better data structure
    protected final Map<String, EntryItem> items = new ConcurrentHashMap<>();

    public MemoryVFSTree() {}

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

        items.put(path, entryItem);
    }

    @Override
    public EntryItem get(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return items.get(path);
    }

    // TODO does do stupid things, gets fixed in future
    @Override
    public Stream<? extends EntryItem> list(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        return items.values().stream().filter(e -> e.getPath().startsWith(path));
    }

    @Override
    public void remove(String path) {

        if (path == null) {
            throw new NullPointerException("path");
        }

        items.remove(path);
    }
}
