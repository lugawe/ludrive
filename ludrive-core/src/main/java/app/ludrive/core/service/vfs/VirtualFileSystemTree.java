package app.ludrive.core.service.vfs;

import java.util.Optional;
import java.util.SequencedCollection;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;

public interface VirtualFileSystemTree {

    void put(String path, EntryItem entryItem);

    void put(EntryItem entryItem);

    Optional<? extends EntryItem> get(String path);

    Optional<Directory> getDirectory(String path);

    Optional<File> getFile(String path);

    SequencedCollection<? extends EntryItem> list(String path);

    SequencedCollection<Directory> listDirectories(String path);

    SequencedCollection<File> listFiles(String path);

    void remove(String path);

    long size();
}
