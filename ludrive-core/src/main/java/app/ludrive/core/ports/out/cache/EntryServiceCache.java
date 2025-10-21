package app.ludrive.core.ports.out.cache;

import java.util.UUID;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.service.cache.Cache;

public interface EntryServiceCache extends Cache<Entry, UUID> {}
