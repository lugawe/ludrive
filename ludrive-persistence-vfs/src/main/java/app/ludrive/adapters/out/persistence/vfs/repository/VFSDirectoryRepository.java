package app.ludrive.adapters.out.persistence.vfs.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.tree.VFSTree;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItem;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

@RequestScoped
public class VFSDirectoryRepository implements DirectoryRepository {

    protected final VFSTree tree;

    @Inject
    public VFSDirectoryRepository(VFSTree tree) {
        this.tree = tree;
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {

        String path = directory.getPath();

        tree.set(path, directory);

        return tree.getDirectory(path);
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {

        Collection<? extends EntryItem> children = tree.getChildren(path);

        return children.stream().filter(i -> i instanceof Directory).map(i -> (Directory) i);
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {

        return tree.getDirectory(path);
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {

        tree.set(path, directory);

        return tree.getDirectory(path);
    }

    @Override
    public Directory deleteDirectory(AuthIdentity identity, UUID entryId, String path) {

        Directory directory = tree.getDirectory(path);

        tree.remove(path);

        return directory;
    }
}
