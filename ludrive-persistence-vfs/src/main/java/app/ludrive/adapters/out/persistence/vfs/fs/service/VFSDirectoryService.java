package app.ludrive.adapters.out.persistence.vfs.fs.service;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.VFSManager;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.ports.out.DirectoryServicePortOut;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

public class VFSDirectoryService implements DirectoryServicePortOut {

    private final VFSManager vfsManager;
    private final DirectoryRepository directoryRepository;

    @Inject
    public VFSDirectoryService(VFSManager vfsManager, DirectoryRepository directoryRepository) {
        this.vfsManager = vfsManager;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {
        return null;
    }

    @Override
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {
        return Stream.of();
    }

    @Override
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {
        return null;
    }

    @Override
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {
        return null;
    }

    @Override
    public EntryItemId deleteDirectory(AuthIdentity identity, UUID entryId, String path) {
        return null;
    }
}
