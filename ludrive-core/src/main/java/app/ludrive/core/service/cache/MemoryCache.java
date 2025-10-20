package app.ludrive.core.service.cache;

import java.util.*;

import app.ludrive.core.domain.Identifiable;
import app.ludrive.core.exception.CacheException;

public class MemoryCache<T extends Identifiable, ID> implements Cache<T, ID> {

    protected final Map<ID, T> cache = Collections.synchronizedMap(new LinkedHashMap<>());

    public MemoryCache() {}

    @Override
    public void setValue(ID id, T value) {

        if (id == null) {
            throw new CacheException("Id cannot be null");
        }

        cache.put(id, value);
    }

    @Override
    public void setValues(Map<ID, T> values) {

        if (values == null) {
            throw new CacheException("Values cannot be null");
        }

        cache.putAll(values);
    }

    @Override
    public Optional<T> getValue(ID id) {

        if (id == null) {
            throw new CacheException("Id cannot be null");
        }

        T value = cache.get(id);

        return Optional.ofNullable(value);
    }

    @Override
    public SequencedCollection<T> getValues(Collection<ID> ids) {

        if (ids == null) {
            throw new CacheException("Ids cannot be null");
        }

        return ids.stream().map(cache::get).filter(Objects::nonNull).toList();
    }

    @Override
    public boolean containsValue(ID id) {

        if (id == null) {
            throw new CacheException("Id cannot be null");
        }

        return cache.containsKey(id);
    }

    @Override
    public void evict(ID id) {

        if (id == null) {
            throw new CacheException("Id cannot be null");
        }

        cache.remove(id);
    }

    @Override
    public void evict(Collection<ID> ids) {

        if (ids == null) {
            throw new CacheException("Ids cannot be null");
        }

        cache.keySet().removeAll(ids);
    }

    @Override
    public void evictAll() {
        cache.clear();
    }
}
