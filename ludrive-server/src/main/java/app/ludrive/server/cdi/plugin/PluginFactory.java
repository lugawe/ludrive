package app.ludrive.server.cdi.plugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

@ApplicationScoped
public class PluginFactory {

    private final Map<String, Plugin> pluginCache = new ConcurrentHashMap<>();

    @Inject
    private Instance<Plugin> plugins;

    public PluginFactory() {}

    @PostConstruct
    public void initializePlugins() {
        for (Plugin plugin : plugins) {
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
    public VirtualFileSystemService produces(ContextService contextService) {

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
            return storagePlugin.createVFSService(configuration);
        }

        throw new IllegalStateException("No StoragePlugin found for type: " + type);
    }
}
