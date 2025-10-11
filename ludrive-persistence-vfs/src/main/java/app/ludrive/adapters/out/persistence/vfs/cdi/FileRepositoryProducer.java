package app.ludrive.adapters.out.persistence.vfs.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.adapters.out.persistence.vfs.repository.VFSFileRepository;
import app.ludrive.adapters.out.persistence.vfs.repository.tree.VFSTree;
import app.ludrive.core.ports.out.repository.FileRepository;

@RequestScoped
public class FileRepositoryProducer {

    public FileRepositoryProducer() {}

    @Produces
    public FileRepository produce(VFSTree tree) {
        return new VFSFileRepository(tree);
    }
}
