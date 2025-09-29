package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.Optional;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.service.event.AbstractEventManager;

public abstract class AbstractCachedService<T, ID> extends AbstractCache<T, CacheKey<ID>>
        implements AbstractEventManager {

    public AbstractCachedService() {}

    protected CacheKey<ID> createCacheKey(AuthIdentity identity, ID id) {
        return new CacheKey<>(identity, id);
    }

    protected abstract T loadValue(AuthIdentity identity, ID id);

    public Optional<T> getValue(AuthIdentity identity, ID id) {

        // TODO performance

        CacheKey<ID> cacheKey = createCacheKey(identity, id);

        Optional<T> optionalValue = getValue(cacheKey);
        if (optionalValue.isPresent()) {
            return optionalValue;
        }

        T value = loadValue(identity, id);

        setValue(cacheKey, value);

        return Optional.ofNullable(value);
    }

    public void evict(AuthIdentity identity, Collection<? extends ID> ids) {

        // TODO performance

        for (ID id : ids) {
            CacheKey<ID> cacheKey = createCacheKey(identity, id);
            evict(cacheKey);
        }
    }
}
