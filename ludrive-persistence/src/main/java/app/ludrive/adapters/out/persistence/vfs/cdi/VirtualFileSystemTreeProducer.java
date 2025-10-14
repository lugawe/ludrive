package app.ludrive.adapters.out.persistence.vfs.cdi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.adapters.out.persistence.vfs.tree.MemoryVirtualFileSystemTree;
import app.ludrive.core.service.context.AuthIdentityEntryKey;
import app.ludrive.core.service.context.ContextService;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

@RequestScoped
public class VirtualFileSystemTreeProducer {

    private static final Map<AuthIdentityEntryKey, VirtualFileSystemTree> cache = new ConcurrentHashMap<>();

    public VirtualFileSystemTreeProducer() {}

    @Produces
    public VirtualFileSystemTree produce(ContextService contextService) {

        AuthIdentityEntryKey key = contextService.getKey();

        return cache.computeIfAbsent(key, (k) -> new MemoryVirtualFileSystemTree());
    }
}
