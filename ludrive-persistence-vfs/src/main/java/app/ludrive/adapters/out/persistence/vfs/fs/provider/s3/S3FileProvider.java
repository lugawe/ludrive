package app.ludrive.adapters.out.persistence.vfs.fs.provider.s3;

import java.util.Collection;
import java.util.List;

import jakarta.inject.Inject;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.FileProvider;

public class S3FileProvider implements FileProvider {

    protected final S3Client s3Client;

    @Inject
    public S3FileProvider(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public FileObject createFileSystem(String scheme, FileObject file, FileSystemOptions fileSystemOptions) {
        return null;
    }

    @Override
    public FileObject findFile(FileObject baseFile, String uri, FileSystemOptions fileSystemOptions) {

        return null;
    }

    @Override
    public Collection<Capability> getCapabilities() {
        return List.of();
    }

    @Override
    public FileSystemConfigBuilder getConfigBuilder() {
        return null;
    }

    @Override
    public FileName parseUri(FileName root, String uri) {
        return null;
    }
}
