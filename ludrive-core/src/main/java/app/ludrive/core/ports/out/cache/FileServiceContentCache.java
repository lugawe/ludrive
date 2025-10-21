package app.ludrive.core.ports.out.cache;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.service.cache.Cache;

public interface FileServiceContentCache extends Cache<Content, String> {}
