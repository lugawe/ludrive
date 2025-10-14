package app.ludrive.adapters.out.persistence.vfs.repository;

import java.util.Objects;
import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.exception.NotFoundException;
import app.ludrive.core.ports.out.repository.DirectoryRepository;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

@RequestScoped
public class VFSDirectoryRepository implements DirectoryRepository {

    protected final VirtualFileSystemTree tree;

    @Inject
    public VFSDirectoryRepository(VirtualFileSystemTree tree) {
        this.tree = Objects.requireNonNull(tree);
    }

    protected NotFoundException createNotFoundException() {
        return new NotFoundException("Entry item not found");
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, Directory directory) {

        String path = directory.getPath();

        tree.put(path, directory);

        return tree.getDirectory(path).orElseThrow(this::createNotFoundException);
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, String path) {

        return tree.listDirectories(path).stream();
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, String path) {

        return tree.getDirectory(path).orElseThrow(this::createNotFoundException);
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, String path, Directory directory) {

        tree.put(path, directory);

        return tree.getDirectory(path).orElseThrow(this::createNotFoundException);
    }

    @Override
    public Directory deleteDirectory(AuthIdentity identity, String path) {

        Directory directory = tree.getDirectory(path).orElseThrow(this::createNotFoundException);

        tree.remove(path);

        return directory;
    }
}
