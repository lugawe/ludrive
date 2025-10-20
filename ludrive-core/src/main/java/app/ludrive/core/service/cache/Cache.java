package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.Optional;
import java.util.SequencedCollection;

import app.ludrive.core.domain.Identifiable;

public interface Cache<T extends Identifiable, ID> {

    void setValue(ID id, T value);

    Optional<T> getValue(ID id);

    SequencedCollection<T> getValues(Collection<ID> ids);

    boolean containsValue(ID id);

    void evict(ID id);

    void evict(Collection<ID> ids);

    void evictAll();
}
