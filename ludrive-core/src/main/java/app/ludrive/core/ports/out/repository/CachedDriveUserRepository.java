package app.ludrive.core.ports.out.repository;

import java.util.Collection;
import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.service.event.Events;

public class CachedDriveUserRepository extends AbstractCachedRepository<DriveUser, UUID>
        implements DriveUserRepository {

    protected final DriveUserRepository driveUserRepository;

    public CachedDriveUserRepository(DriveUserRepository driveUserRepository) {
        this.driveUserRepository = driveUserRepository;
    }

    @Override
    public DriveUser createDriveUser(DriveUser driveUser) {
        return driveUserRepository.createDriveUser(driveUser);
    }

    @Override
    public DriveUser getDriveUser(UUID driveUserId) {
        return cache.computeIfAbsent(driveUserId, driveUserRepository::getDriveUser);
    }

    @Override
    public DriveUser updateDriveUser(UUID driveUserId, DriveUser driveUser) {
        return driveUserRepository.updateDriveUser(driveUserId, driveUser);
    }

    @Override
    public UUID deleteDriveUser(UUID driveUserId) {
        return driveUserRepository.deleteDriveUser(driveUserId);
    }

    @Override
    public void onDriveUserCreated(Events.DriveUserCreatedProps props) {}

    @Override
    public void onDriveUserRead(Events.DriveUserReadProps props) {}

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
