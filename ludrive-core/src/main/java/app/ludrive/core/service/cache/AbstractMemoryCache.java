package app.ludrive.core.service.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import app.ludrive.core.exception.CacheException;

public abstract class AbstractMemoryCache<T, ID> implements Cache<T, ID> {

    protected final Map<ID, T> cache = new ConcurrentHashMap<>();

    protected AbstractMemoryCache() {}

    @Override
    public void setValue(ID id, T value) {
        cache.put(id, value);
    }

    @Override
    public T getValue(ID id) {
        T value = cache.get(id);
        if (value == null) {
            throw new CacheException("id not found in cache");
        }
        return value;
    }

    @Override
    public boolean containsValue(ID id) {
        return cache.containsKey(id);
    }

    @Override
    public void evict(ID id) {
        cache.remove(id);
    }

    @Override
    public void evictAll() {
        cache.clear();
    }
}
