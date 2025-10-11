package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.ports.out.DefaultDirectoryServicePortOut;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.core.ports.out.repository.DirectoryRepository;
import app.ludrive.core.service.logging.Logger;
import app.ludrive.core.service.vfs.VirtualFileSystemService;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class DirectoryServicePortOutProducer {

    @Inject
    @ClassNamed(DirectoryServicePortOut.class)
    private Logger logger;

    @Inject
    private MigrationHandler migrationHandler;

    @Inject
    private DirectoryRepository directoryRepository;

    @Inject
    private VirtualFileSystemService virtualFileSystemService;

    public DirectoryServicePortOutProducer() {}

    @Produces
    public DirectoryServicePortOut produce() {

        return new DefaultDirectoryServicePortOut(
                logger, migrationHandler, directoryRepository, virtualFileSystemService);
    }
}
