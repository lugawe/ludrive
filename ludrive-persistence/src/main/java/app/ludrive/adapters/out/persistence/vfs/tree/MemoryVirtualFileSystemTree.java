package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Optional;
import java.util.SequencedCollection;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.Exceptions;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

public class MemoryVirtualFileSystemTree implements VirtualFileSystemTree {

    protected final Trie<EntryItem> trie;
    protected final Validator validator;

    public MemoryVirtualFileSystemTree(Validator validator) {
        this.trie = new Trie<>("/");
        this.validator = validator;
    }

    @Override
    public void put(String path, EntryItem entryItem) {

        if (path == null) {
            throw Exceptions.createNullPointer("path");
        }

        if (entryItem == null) {
            throw Exceptions.createNullPointer("entryItem");
        }

        if (!path.equals(entryItem.getPath())) {
            throw new IllegalStateException("Path and entry item path must match");
        }

        trie.put(path, entryItem);
    }

    @Override
    public void put(EntryItem entryItem) {

        if (entryItem == null) {
            throw Exceptions.createNullPointer("entryItem");
        }

        put(entryItem.getPath(), entryItem);
    }

    @Override
    public Optional<? extends EntryItem> get(String path) {

        if (path == null) {
            throw Exceptions.createNullPointer("path");
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
            throw Exceptions.createNullPointer("path");
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
            throw Exceptions.createNullPointer("path");
        }

        trie.remove(path);
    }

    @Override
    public long size() {
        return trie.size();
    }
}
