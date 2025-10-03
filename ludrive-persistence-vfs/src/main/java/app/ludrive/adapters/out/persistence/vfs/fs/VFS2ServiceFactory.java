package app.ludrive.adapters.out.persistence.vfs.fs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.exception.VFSException;
import app.ludrive.core.service.context.AuthIdentityEntryKey;
import app.ludrive.core.service.context.ContextService;
import app.ludrive.core.service.vfs.VirtualFSService;
import app.ludrive.core.service.vfs.VirtualFSServiceFactory;

// TODO
@RequestScoped
public class VFS2ServiceFactory implements VirtualFSServiceFactory {

    @Inject
    private ContextService contextService;

    public VFS2ServiceFactory() {}

    @Produces
    public VirtualFSService produce() {

        AuthIdentityEntryKey key = contextService.getKey();

        return create(key);
    }

    @Override
    public VirtualFSService create(AuthIdentityEntryKey key) throws VFSException {
        return new VFS2Service();
    }
}
