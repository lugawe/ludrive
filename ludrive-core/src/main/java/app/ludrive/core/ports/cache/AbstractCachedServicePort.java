package app.ludrive.core.ports.cache;

import java.util.Collection;
import java.util.Optional;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.service.cache.AbstractCache;
import app.ludrive.core.service.event.EventManager;
import app.ludrive.core.service.event.Events;

public abstract class AbstractCachedServicePort<T, ID> extends AbstractCache<T, ServicePortCacheKey<ID>>
        implements EventManager {

    public AbstractCachedServicePort() {}

    public abstract T loadValue(AuthIdentity identity, ID id);

    public T cacheGet(AuthIdentity identity, ID id) {

        // TODO performance

        ServicePortCacheKey<ID> servicePortCacheKey = createServicePortCacheKey(identity, id);

        Optional<T> optionalValue = getValue(servicePortCacheKey);
        if (optionalValue.isPresent()) {
            return optionalValue.get();
        }

        T value = loadValue(identity, id);

        setValue(servicePortCacheKey, value);

        return value;
    }

    public void cacheEvict(AuthIdentity identity, Collection<? extends ID> ids) {

        // TODO performance

        for (ID id : ids) {
            ServicePortCacheKey<ID> servicePortCacheKey = createServicePortCacheKey(identity, id);
            evict(servicePortCacheKey);
        }
    }

    private ServicePortCacheKey<ID> createServicePortCacheKey(AuthIdentity identity, ID id) {
        return new ServicePortCacheKey<>(identity, id);
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {}

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {}

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {}

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {}

    @Override
    public void onEntryCreated(Events.EntryCreatedProps props) {}

    @Override
    public void onEntryRead(Events.EntryReadProps props) {}

    @Override
    public void onEntryUpdated(Events.EntryUpdatedProps props) {}

    @Override
    public void onEntryDeleted(Events.EntryDeletedProps props) {}

    @Override
    public void onDirectoryCreated(Events.DirectoryCreatedProps props) {}

    @Override
    public void onDirectoryRead(Events.DirectoryReadProps props) {}

    @Override
    public void onDirectoryUpdated(Events.DirectoryUpdatedProps props) {}

    @Override
    public void onDirectoryDeleted(Events.DirectoryDeletedProps props) {}

    @Override
    public void onFileCreated(Events.FileCreatedProps props) {}

    @Override
    public void onFileRead(Events.FileReadProps props) {}

    @Override
    public void onFileUpdated(Events.FileUpdatedProps props) {}

    @Override
    public void onFileDeleted(Events.FileDeletedProps props) {}
}
