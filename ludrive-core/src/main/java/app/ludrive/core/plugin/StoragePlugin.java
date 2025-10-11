package app.ludrive.core.plugin;

import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.service.vfs.VirtualFileSystemService;

public interface StoragePlugin extends Plugin {

    VirtualFileSystemService createVFSService(EntryConfiguration configuration);
}
