package app.ludrive.adapters.out.persistence.vfs.fs;

import java.io.Closeable;
import java.net.URI;

import app.ludrive.adapters.out.persistence.vfs.fs.provider.FileProviders;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.exception.VFSAccessException;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;

public class VFSManager implements Closeable {

    private final Entry entry;
    private final FileSystemManager fileSystemManager;

    public VFSManager(Entry entry) throws Exception {
        this.entry = entry;
        this.fileSystemManager = new DefaultFileSystemManager();
        this.init();
    }

    private void init() throws Exception {
        Entry.Protocol protocol = entry.getProtocol();
        DefaultFileSystemManager defaultFileSystemManager = (DefaultFileSystemManager) fileSystemManager;
        defaultFileSystemManager.addProvider(protocol.getUrlScheme(), FileProviders.resolve(protocol));
        defaultFileSystemManager.init();
    }

    @Override
    public void close() {
        fileSystemManager.close();
    }

    protected FileObject resolve(String path) {
        if (path == null || !path.startsWith("/")) {
            throw new IllegalArgumentException("path");
        }
        try {
            String scheme = entry.getProtocol().getUrlScheme();
            URI uri = new URI(String.format("%s://%s", scheme, path));
            return fileSystemManager.resolveFile(uri);
        } catch (Exception e) {
            throw new VFSAccessException("resolve", e);
        }
    }

    protected Directory convertToDirectory(FileObject fileObject) {

        return null;
    }

    public Directory createDirectory(Directory directory) {
        String path = directory.getPath();
        try (FileObject dir = resolve(path)) {
            if (!dir.exists()) {
                dir.createFolder();
            }
            return convertToDirectory(dir);
        } catch (FileSystemException e) {
            throw new VFSAccessException("createDirectory", e);
        }
    }
}
