package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.DefaultFileServicePortOut;
import app.ludrive.core.ports.out.FileServicePortOut;
import app.ludrive.core.ports.out.repository.FileRepository;
import app.ludrive.core.service.vfs.VirtualFSService;
import app.ludrive.server.cdi.util.ClassNamed;

@RequestScoped
public class QuarkusFileServicePortOut {

    @Inject
    @ClassNamed(FileServicePortOut.class)
    private Logger logger;

    @Inject
    private FileRepository fileRepository;

    @Inject
    private VirtualFSService virtualFSService;

    public QuarkusFileServicePortOut() {}

    @Produces
    public FileServicePortOut produce() {

        return new DefaultFileServicePortOut(logger, fileRepository, virtualFSService);
    }
}
