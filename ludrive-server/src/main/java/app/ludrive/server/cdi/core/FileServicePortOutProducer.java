package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.ports.out.CachedFileServicePortOut;
import app.ludrive.core.ports.out.DefaultFileServicePortOut;
import app.ludrive.core.ports.out.FileServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.cache.Cache;
import app.ludrive.core.service.cache.MemoryCache;
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
    public Cache<File, String> fileServiceFileCache() {
        return new MemoryCache<>();
    }

    @Produces
    public Cache<Content, String> fileServiceContentCache() {
        return new MemoryCache<>();
    }

    @Produces
    public FileServicePortOut produce(
            Cache<File, String> fileServiceFileCache, Cache<Content, String> fileServiceContentCache) {

        FileServicePortOut fileServicePortOut =
                new DefaultFileServicePortOut(logger, migrationHandler, fileRepository, virtualFileSystemService);

        return new CachedFileServicePortOut(fileServicePortOut, fileServiceFileCache, fileServiceContentCache);
    }
}
