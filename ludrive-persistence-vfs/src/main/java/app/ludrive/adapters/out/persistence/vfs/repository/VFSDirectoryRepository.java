package app.ludrive.adapters.out.persistence.vfs.repository;

import java.util.Objects;
import java.util.stream.Stream;

import app.ludrive.adapters.out.persistence.vfs.repository.tree.VFSTree;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

public class VFSDirectoryRepository implements DirectoryRepository {

    protected final VFSTree tree;

    public VFSDirectoryRepository(VFSTree tree) {
        this.tree = Objects.requireNonNull(tree);
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, Directory directory) {

        String path = directory.getPath();

        tree.put(path, directory);

        return tree.getDirectory(path);
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, String path) {

        return tree.listDirectories(path);
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, String path) {

        return tree.getDirectory(path);
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, String path, Directory directory) {

        tree.put(path, directory);

        return tree.getDirectory(path);
    }

    @Override
    public Directory deleteDirectory(AuthIdentity identity, String path) {

        Directory directory = tree.getDirectory(path);

        tree.remove(path);

        return directory;
    }
}
