package app.ludrive.core.service.cache;

import java.util.Collection;

public interface Cache<T, ID> {

    void setValue(ID id, T value);

    T getValue(ID id);

    boolean containsValue(ID id);

    void evict(ID id);

    void evict(Collection<? extends ID> ids);

    void evictAll();
}
