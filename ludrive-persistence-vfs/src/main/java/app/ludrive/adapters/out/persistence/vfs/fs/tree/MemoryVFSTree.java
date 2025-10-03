package app.ludrive.adapters.out.persistence.vfs.fs.tree;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.RequestScoped;

import app.ludrive.core.domain.vfs.EntryItem;

@RequestScoped
public class MemoryVFSTree implements VFSTree {

    // TODO better data structure
    protected final Map<String, EntryItem> items = new ConcurrentHashMap<>();

    public MemoryVFSTree() {}

    @Override
    public void set(String path, EntryItem entryItem) {
        items.put(path, entryItem);
    }

    @Override
    public EntryItem get(String path) {
        return items.get(path);
    }

    @Override
    public SequencedCollection<? extends EntryItem> getChildren(String path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(String path) {
        items.remove(path);
    }
}
