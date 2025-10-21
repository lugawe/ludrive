package app.ludrive.core.ports.out.cache;

import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.service.cache.Cache;

public interface FileServiceFileCache extends Cache<File, String> {}
