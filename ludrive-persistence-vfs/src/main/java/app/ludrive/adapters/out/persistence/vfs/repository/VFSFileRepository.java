package app.ludrive.adapters.out.persistence.vfs.repository;

import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.tree.VFSTree;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.ports.out.repository.FileRepository;

@RequestScoped
public class VFSFileRepository implements FileRepository {

    protected final VFSTree tree;

    @Inject
    public VFSFileRepository(VFSTree tree) {
        this.tree = tree;
    }

    @Override
    public File createFile(AuthIdentity identity, File file) {

        String path = file.getPath();

        tree.put(path, file);

        return tree.getFile(path);
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, String path) {

        return tree.listFiles(path);
    }

    @Override
    public File getFile(AuthIdentity identity, String path) {

        return tree.getFile(path);
    }

    @Override
    public File updateFile(AuthIdentity identity, String path, File file) {

        tree.put(path, file);

        return tree.getFile(path);
    }

    @Override
    public File deleteFile(AuthIdentity identity, String path) {

        File file = tree.getFile(path);

        tree.remove(path);

        return file;
    }
}
