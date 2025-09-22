package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.DefaultDirectoryServicePortOut;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.ports.out.repository.DirectoryRepository;
import app.ludrive.core.service.vfs.VirtualFSService;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class QuarkusDirectoryServicePortOut {

    @Inject
    @ClassNamed(DirectoryServicePortOut.class)
    private Logger logger;

    @Inject
    private DirectoryRepository directoryRepository;

    @Inject
    private VirtualFSService virtualFSService;

    public QuarkusDirectoryServicePortOut() {}

    @Produces
    public DirectoryServicePortOut produce() {

        return new DefaultDirectoryServicePortOut(logger, directoryRepository, virtualFSService);
    }
}
