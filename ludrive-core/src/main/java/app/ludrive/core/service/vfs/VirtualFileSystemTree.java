package app.ludrive.core.service.vfs;

import java.util.stream.Stream;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.domain.vfs.File;

public interface VirtualFileSystemTree {

    void put(String path, EntryItem entryItem);

    EntryItem get(String path);

    Directory getDirectory(String path);

    File getFile(String path);

    Stream<? extends EntryItem> list(String path);

    Stream<Directory> listDirectories(String path);

    Stream<File> listFiles(String path);

    void remove(String path);
}
