package app.ludrive.server.cdi.plugin;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.plugin.Plugin;
import app.ludrive.core.plugin.StoragePlugin;
import app.ludrive.core.service.context.ContextService;
import app.ludrive.core.service.vfs.VirtualFileSystemService;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

@ApplicationScoped
public class StoragePluginFactory {

    private final Map<String, StoragePlugin> pluginCache = new LinkedHashMap<>();

    @Inject
    private Instance<StoragePlugin> plugins;

    public StoragePluginFactory() {}

    @PostConstruct
    public void initializePlugins() {
        for (StoragePlugin plugin : plugins) {
            String pluginId = plugin.getPluginId();
            Plugin previous = pluginCache.putIfAbsent(pluginId, plugin);
            if (previous != null) {
                throw new IllegalStateException("Duplicate plugin ID detected: " + pluginId);
            }
            plugin.initialize();
        }
    }

    @Produces
    @RequestScoped
    public VirtualFileSystemService produces(ContextService contextService, VirtualFileSystemTree tree) {

        Entry entry = contextService.getEntry();
        if (entry == null) {
            throw new IllegalStateException("Entry is null");
        }

        EntryConfiguration configuration = entry.getConfiguration();
        if (configuration == null) {
            throw new IllegalStateException("Entry configuration is null");
        }

        String type = configuration.getType();

        Plugin plugin = pluginCache.get(type);
        if (plugin instanceof StoragePlugin storagePlugin) {
            VirtualFileSystemService service = storagePlugin.createVFSService(configuration);
            service.initialize(tree);
            return service;
        }

        throw new IllegalStateException("No StoragePlugin found for type: " + type);
    }
}
