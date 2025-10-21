package app.ludrive.core.ports.out.cache;

import java.util.UUID;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.service.cache.MemoryCache;

public class MemoryEntryServiceCache extends MemoryCache<Entry, UUID> implements EntryServiceCache {}
