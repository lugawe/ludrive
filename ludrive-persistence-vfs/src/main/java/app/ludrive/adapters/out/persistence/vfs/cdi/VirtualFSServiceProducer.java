package app.ludrive.adapters.out.persistence.vfs.cdi;

import app.ludrive.core.domain.management.Entry;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.VFS2Service;
import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.service.context.ContextService;
import app.ludrive.core.service.vfs.VirtualFSService;

@RequestScoped
public class VirtualFSServiceProducer {

    @Inject
    private ContextService contextService;

    public VirtualFSServiceProducer() {}

    @Produces
    public VirtualFSService produce() {

        Entry entry = contextService.getEntry();

        return new VFS2Service(entry, "ram://");
    }
}
