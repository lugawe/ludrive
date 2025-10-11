package app.ludrive.core.service.vfs;

import java.util.stream.Stream;

import app.ludrive.core.domain.vfs.EntryItem;

public interface VirtualFileSystemTree {

    void put(String path, EntryItem entryItem);

    EntryItem get(String path);

    Stream<? extends EntryItem> list(String path);

    void remove(String path);
}
