package app.ludrive.adapters.out.persistence.vfs.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.adapters.out.persistence.vfs.fs.tree.VFSTree;
import app.ludrive.adapters.out.persistence.vfs.repository.VFSDirectoryRepository;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

@RequestScoped
public class VFSDirectoryRepositoryProducer {

    public VFSDirectoryRepositoryProducer() {}

    @Produces
    public DirectoryRepository produce(VFSTree tree) {
        return new VFSDirectoryRepository(tree);
    }
}
