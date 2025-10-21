package app.ludrive.core.ports.out.cache;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.service.cache.MemoryCache;

public class MemoryDirectoryServiceCache extends MemoryCache<Directory, String> implements DirectoryServiceCache {}
