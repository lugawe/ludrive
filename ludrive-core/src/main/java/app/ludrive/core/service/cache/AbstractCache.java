package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import app.ludrive.core.exception.CacheException;

public abstract class AbstractCache<T, ID> implements Cache<T, ID> {

    protected final Map<ID, T> cache = new ConcurrentHashMap<>();

    protected AbstractCache() {}

    @Override
    public void setValue(ID id, T value) {

        if (id == null) {
            throw new CacheException("id cannot be null");
        }

        cache.put(id, value);
    }

    @Override
    public Optional<T> getValue(ID id) {

        if (id == null) {
            throw new CacheException("id cannot be null");
        }

        T value = cache.get(id);

        return Optional.ofNullable(value);
    }

    @Override
    public boolean containsValue(ID id) {

        if (id == null) {
            throw new CacheException("id cannot be null");
        }

        return cache.containsKey(id);
    }

    @Override
    public void evict(ID id) {

        if (id == null) {
            throw new CacheException("id cannot be null");
        }

        cache.remove(id);
    }

    @Override
    public void evict(Collection<? extends ID> ids) {

        if (ids == null) {
            throw new CacheException("ids cannot be null");
        }

        cache.keySet().removeAll(ids);
    }

    @Override
    public void evictAll() {
        cache.clear();
    }
}
