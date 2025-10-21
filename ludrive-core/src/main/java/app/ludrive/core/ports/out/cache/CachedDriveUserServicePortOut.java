package app.ludrive.core.ports.out.cache;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.service.event.AbstractEventListener;
import app.ludrive.core.service.event.Events;

public final class CachedDriveUserServicePortOut implements DriveUserServicePortOut, AbstractEventListener {

    private final DriveUserServicePortOut driveUserServicePortOut;
    private final DriveUserServiceCache driveUserServiceCache;

    public CachedDriveUserServicePortOut(
            DriveUserServicePortOut driveUserServicePortOut, DriveUserServiceCache driveUserServiceCache) {
        this.driveUserServicePortOut = driveUserServicePortOut;
        this.driveUserServiceCache = driveUserServiceCache;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {
        return driveUserServicePortOut.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(UUID driveUserId) {

        Supplier<DriveUser> loader = () -> driveUserServicePortOut.getDriveUser(driveUserId);

        return driveUserServiceCache.computeIfAbsent(driveUserId, loader);
    }

    @Override
    public DriveUser updateDriveUser(UUID driveUserId, DriveUser updatedDriveUser) {
        return driveUserServicePortOut.updateDriveUser(driveUserId, updatedDriveUser);
    }

    @Override
    public DriveUser deleteDriveUser(UUID driveUserId) {
        return driveUserServicePortOut.deleteDriveUser(driveUserId);
    }

    @Override
    public void onDriveUserUpdated(Events.DriveUserUpdatedProps props) {

        List<UUID> keys = props.updatedDriveUsers().stream()
                .flatMap(e -> Stream.of(e.oldValue().getId(), e.newValue().getId()))
                .distinct()
                .toList();

        driveUserServiceCache.evict(keys);
    }

    @Override
    public void onDriveUserDeleted(Events.DriveUserDeletedProps props) {

        List<UUID> keys =
                props.deletedDriveUsers().stream().map(e -> e.value().getId()).toList();

        driveUserServiceCache.evict(keys);
    }
}
