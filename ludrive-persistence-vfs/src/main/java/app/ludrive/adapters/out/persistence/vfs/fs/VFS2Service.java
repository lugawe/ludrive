package app.ludrive.adapters.out.persistence.vfs.fs;

import java.io.InputStream;
import java.io.OutputStream;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

import org.apache.commons.vfs2.*;

public class VFS2Service implements VirtualFileSystemService {

    protected final FileSystemManager fileSystemManager;
    protected final FileObject root;

    public VFS2Service(String rootLocation) throws VFSException {
        if (rootLocation == null) {
            throw new NullPointerException("rootLocation");
        }
        try {
            this.fileSystemManager = VFS.getManager();
            this.root = fileSystemManager.resolveFile(rootLocation);
        } catch (Exception e) {
            throw new VFSException("failed to initialize VFS2Service", e);
        }
    }

    private FileObject resolve(String path) throws FileSystemException {
        if (path == null || path.isBlank() || "/".equals(path)) {
            return root;
        }
        String p = path.startsWith("/") ? path.substring(1) : path;
        return fileSystemManager.resolveFile(root, p);
    }

    private void checkClose(FileObject file) {}

    @Override
    public void createDirectory(Directory directory) throws VFSException {
        try {

            FileObject newDirectory = resolve(directory.getPath());
            if (newDirectory.exists()) {
                throw new VFSException("directory already exists");
            }

            newDirectory.createFolder();

            checkClose(newDirectory);

        } catch (Exception e) {
            throw new VFSException("failed to create directory", e);
        }
    }

    @Override
    public void updateDirectory(String path, Directory directory) throws VFSException {
        try {

            FileObject existingDirectory = resolve(path);
            if (!existingDirectory.exists() || !existingDirectory.isFolder()) {
                throw new VFSException("cannot resolve existing directory");
            }

            FileObject newDirectory = resolve(directory.getPath());
            existingDirectory.moveTo(newDirectory);

            checkClose(newDirectory);
            checkClose(existingDirectory);

        } catch (Exception e) {
            throw new VFSException("failed to update directory", e);
        }
    }

    @Override
    public void deleteDirectory(String path) throws VFSException {
        try {

            FileObject existingDirectory = resolve(path);
            if (!existingDirectory.exists() || !existingDirectory.isFolder()) {
                throw new VFSException("cannot resolve existing directory");
            }

            existingDirectory.delete(Selectors.SELECT_ALL);

            checkClose(existingDirectory);

        } catch (Exception e) {
            throw new VFSException("failed to delete directory", e);
        }
    }

    @Override
    public void createFile(File file) throws VFSException {
        try {

            FileObject newFile = resolve(file.getPath());
            if (newFile.exists()) {
                throw new VFSException("file already exists");
            }

            newFile.createFile();

            checkClose(newFile);

        } catch (Exception e) {
            throw new VFSException("failed to create file", e);
        }
    }

    @Override
    public void updateFile(String path, File file) throws VFSException {
        try {

            FileObject existingFile = resolve(path);
            if (!existingFile.exists() || !existingFile.isFile()) {
                throw new VFSException("cannot resolve existing file");
            }

            FileObject newFile = resolve(file.getPath());
            existingFile.moveTo(newFile);

            checkClose(newFile);
            checkClose(existingFile);

        } catch (Exception e) {
            throw new VFSException("failed to update file", e);
        }
    }

    @Override
    public void deleteFile(String path) throws VFSException {
        try {

            FileObject existingFile = resolve(path);
            if (!existingFile.exists() || !existingFile.isFile()) {
                throw new VFSException("cannot resolve existing file");
            }

            existingFile.delete();

            checkClose(existingFile);

        } catch (Exception e) {
            throw new VFSException("failed to delete file", e);
        }
    }

    @Override
    public void updateFileContent(String path, Content content) throws VFSException {
        try {

            FileObject existingFile = resolve(path);
            if (!existingFile.exists() || !existingFile.isFile()) {
                throw new VFSException("cannot resolve existing file");
            }

            try (OutputStream outputStream = existingFile.getContent().getOutputStream()) {
                content.writeTo(outputStream);
            }

            checkClose(existingFile);

        } catch (Exception e) {
            throw new VFSException("failed to update file content", e);
        }
    }

    @Override
    public Content getFileContent(String path) throws VFSException {
        try {

            FileObject existingFile = resolve(path);
            if (!existingFile.exists() || !existingFile.isFile()) {
                throw new VFSException("cannot resolve existing file");
            }

            InputStream inputStream = existingFile.getContent().getInputStream();
            Content content = Content.from(inputStream);

            checkClose(existingFile);

            return content;

        } catch (Exception e) {
            throw new VFSException("failed to get file content", e);
        }
    }
}
