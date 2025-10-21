package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.function.Supplier;

public interface Cache<T, ID> {

    // ---

    void putValue(ID id, T value);

    void putValues(Map<ID, T> values);

    Optional<T> getValue(ID id);

    SequencedCollection<T> getValues(Collection<ID> ids);

    boolean containsKey(ID id);

    void evict(ID id);

    void evict(Collection<ID> ids);

    void evictAll();

    int size();

    // --- default ---

    default T computeIfAbsent(ID id, Supplier<T> loader) {
        return getValue(id).orElseGet(() -> {
            T value = loader.get();
            putValue(id, value);
            return value;
        });
    }
}
