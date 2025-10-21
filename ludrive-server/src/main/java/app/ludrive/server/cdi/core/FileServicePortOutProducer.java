package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.out.DefaultFileServicePortOut;
import app.ludrive.core.ports.out.FileServicePortOut;
import app.ludrive.core.ports.out.cache.*;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFileSystemService;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class FileServicePortOutProducer {

    @Inject
    @ClassNamed(FileServicePortOut.class)
    private Logger logger;

    @Inject
    private MigrationHandler migrationHandler;

    @Inject
    private FileRepository fileRepository;

    @Inject
    private VirtualFileSystemService virtualFileSystemService;

    public FileServicePortOutProducer() {}

    @Produces
    public FileServiceFileCache fileServiceFileCache() {
        return new MemoryFileServiceFileCache();
    }

    @Produces
    public FileServiceContentCache fileServiceContentCache() {
        return new MemoryFileServiceContentCache();
    }

    @Produces
    public CachedFileServicePortOut produce(
            FileServiceFileCache fileServiceFileCache, FileServiceContentCache fileServiceContentCache) {

        FileServicePortOut fileServicePortOut =
                new DefaultFileServicePortOut(logger, migrationHandler, fileRepository, virtualFileSystemService);

        return new CachedFileServicePortOut(fileServicePortOut, fileServiceFileCache, fileServiceContentCache);
    }
}
