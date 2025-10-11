package app.ludrive.adapters.out.persistence.vfs.cdi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.repository.tree.MemoryVFSTree;
import app.ludrive.adapters.out.persistence.vfs.repository.tree.VFSTree;
import app.ludrive.core.service.context.AuthIdentityEntryKey;
import app.ludrive.core.service.context.ContextService;

@RequestScoped
public class VFSTreeProducer {

    private static final Map<AuthIdentityEntryKey, VFSTree> cache = new ConcurrentHashMap<>();

    @Inject
    private ContextService contextService;

    public VFSTreeProducer() {}

    @Produces
    public VFSTree produce() {

        AuthIdentityEntryKey key = contextService.getKey();

        return cache.computeIfAbsent(key, (k) -> new MemoryVFSTree());
    }
}
