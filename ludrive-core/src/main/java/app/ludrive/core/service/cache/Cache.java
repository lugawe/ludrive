package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.SequencedCollection;

public interface Cache<T, ID> {

    void setValue(ID id, T value);

    void setValues(Map<ID, T> values);

    Optional<T> getValue(ID id);

    SequencedCollection<T> getValues(Collection<ID> ids);

    boolean containsValue(ID id);

    void evict(ID id);

    void evict(Collection<ID> ids);

    void evictAll();
}
