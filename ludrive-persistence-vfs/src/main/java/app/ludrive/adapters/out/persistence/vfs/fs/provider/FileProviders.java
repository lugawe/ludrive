package app.ludrive.adapters.out.persistence.vfs.fs.provider;

import app.ludrive.core.domain.management.Entry;

import org.apache.commons.vfs2.provider.FileProvider;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;
import org.apache.commons.vfs2.provider.ram.RamFileProvider;

public final class FileProviders {

    private FileProviders() {}

    public static FileProvider resolve(Entry.Protocol protocol) {

        return switch (protocol) {
            case FILE -> new DefaultLocalFileProvider();
            case MEMORY, S3 -> new RamFileProvider();
            case null, default -> throw new IllegalArgumentException("protocol not found");
        };
    }
}
