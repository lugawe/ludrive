package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Optional;
import java.util.SequencedCollection;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

public class MemoryVirtualFileSystemTree implements VirtualFileSystemTree {

    protected final Trie<EntryItem> trie;

    public MemoryVirtualFileSystemTree() {
        this.trie = new Trie<>("/");
    }

    @Override
    public void put(String path, EntryItem entryItem) {

        if (path == null) {
            throw new NullPointerException("Parameter path cannot be null");
        }

        if (entryItem == null) {
            throw new NullPointerException("Parameter entryItem cannot be null");
        }

        if (!path.equals(entryItem.getPath())) {
            throw new IllegalArgumentException("Path and entry item path must match");
        }

        trie.put(path, entryItem);
    }

    @Override
    public Optional<? extends EntryItem> get(String path) {

        if (path == null) {
            throw new NullPointerException("Parameter path cannot be null");
        }

        return trie.get(path);
    }

    @Override
    public Optional<Directory> getDirectory(String path) {

        return get(path).filter(i -> i instanceof Directory).map(i -> (Directory) i);
    }

    @Override
    public Optional<File> getFile(String path) {

        return get(path).filter(i -> i instanceof File).map(i -> (File) i);
    }

    @Override
    public SequencedCollection<? extends EntryItem> list(String path) {

        if (path == null) {
            throw new NullPointerException("Parameter path cannot be null");
        }

        return trie.listDirectChildren(path);
    }

    @Override
    public SequencedCollection<Directory> listDirectories(String path) {

        return list(path).stream()
                .filter(i -> i instanceof Directory)
                .map(i -> (Directory) i)
                .toList();
    }

    @Override
    public SequencedCollection<File> listFiles(String path) {

        return list(path).stream()
                .filter(i -> i instanceof File)
                .map(i -> (File) i)
                .toList();
    }

    @Override
    public void remove(String path) {

        if (path == null) {
            throw new NullPointerException("Parameter path cannot be null");
        }

        trie.remove(path);
    }
}
