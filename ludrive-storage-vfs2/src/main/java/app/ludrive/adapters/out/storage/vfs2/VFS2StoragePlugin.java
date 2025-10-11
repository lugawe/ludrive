package app.ludrive.adapters.out.storage.vfs2;

import jakarta.enterprise.context.ApplicationScoped;

import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.plugin.StoragePlugin;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

@ApplicationScoped
public class VFS2StoragePlugin implements StoragePlugin {

    private static final String PLUGIN_ID = "VFS2";

    public VFS2StoragePlugin() {}

    @Override
    public final String getPluginId() {
        return PLUGIN_ID;
    }

    @Override
    public void initialize() {}

    @Override
    public VirtualFileSystemService createVFSService(EntryConfiguration configuration) {

        String rootLocation = configuration.getRootLocation();

        return new VFS2Service(rootLocation);
    }
}
