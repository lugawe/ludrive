package app.ludrive.core.ports.out.cache;

import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.service.cache.MemoryCache;

public class MemoryFileServiceFileCache extends MemoryCache<File, String> implements FileServiceFileCache {}
