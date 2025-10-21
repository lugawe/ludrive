package app.ludrive.core.ports.out.cache;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.service.cache.MemoryCache;

public class MemoryFileServiceContentCache extends MemoryCache<Content, String> implements FileServiceContentCache {}
