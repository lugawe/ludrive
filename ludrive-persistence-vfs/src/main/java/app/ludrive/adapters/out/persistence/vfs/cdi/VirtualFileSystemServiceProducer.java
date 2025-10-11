package app.ludrive.adapters.out.persistence.vfs.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.VFS2Service;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.service.context.ContextService;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

// TODO make it better
@RequestScoped
public class VirtualFileSystemServiceProducer {

    @Inject
    private ContextService contextService;

    public VirtualFileSystemServiceProducer() {}

    @Produces
    public VirtualFileSystemService produce() {

        Entry entry = contextService.getEntry();
        EntryConfiguration configuration = entry.getConfiguration();

        String location =
                switch (configuration.getType()) {
                    case MEMORY -> String.format("ram://%s/%s", configuration.getRootLocation(), entry.getId());
                    case LOCAL -> String.format("file://%s/%s", configuration.getRootLocation(), entry.getId());
                    case FTP, S3 -> throw new UnsupportedOperationException();
                };

        return new VFS2Service(location);
    }
}
