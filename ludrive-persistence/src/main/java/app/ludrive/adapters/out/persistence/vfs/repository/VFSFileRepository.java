package app.ludrive.adapters.out.persistence.vfs.repository;

import java.util.Objects;
import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.DomainExceptions;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

@RequestScoped
public class VFSFileRepository implements FileRepository {

    protected final VirtualFileSystemTree tree;

    @Inject
    public VFSFileRepository(VirtualFileSystemTree tree) {
        this.tree = Objects.requireNonNull(tree);
    }

    @Override
    public File createFile(DriveUser driveUser, File file) {

        String path = file.getPath();

        tree.put(path, file);

        return tree.getFile(path).orElseThrow(() -> DomainExceptions.createFileNotFound(path));
    }

    @Override
    public Stream<File> getFiles(DriveUser driveUser, String path) {

        return tree.listFiles(path).stream();
    }

    @Override
    public File getFile(DriveUser driveUser, String path) {

        return tree.getFile(path).orElseThrow(() -> DomainExceptions.createFileNotFound(path));
    }

    @Override
    public File updateFile(DriveUser driveUser, String path, File file) {

        tree.put(path, file);

        return tree.getFile(path).orElseThrow(() -> DomainExceptions.createFileNotFound(path));
    }

    @Override
    public File deleteFile(DriveUser driveUser, String path) {

        File file = tree.getFile(path).orElseThrow(() -> DomainExceptions.createFileNotFound(path));

        tree.remove(path);

        return file;
    }
}
