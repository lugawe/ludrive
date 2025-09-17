package app.ludrive.core.service.cache;

import java.util.Collection;
import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.service.event.Events;

public class DefaultDriveUserCache extends AbstractCache<DriveUser, UUID> implements DriveUserCache {

    public DefaultDriveUserCache() {}

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {
        Collection<UUID> driveUserIds = props.driveUserIds();
        evict(driveUserIds);
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {
        Collection<UUID> driveUserIds = props.driveUserIds();
        evict(driveUserIds);
    }
}
