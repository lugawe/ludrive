package app.ludrive.adapters.out.persistence.vfs.fs.service;

import java.io.InputStream;
import java.util.UUID;
import java.util.stream.Stream;

import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.fs.VFSManager;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.ports.out.FileServicePortOut;

public class VFSFileService implements FileServicePortOut {

    private final VFSManager vfsManager;

    @Inject
    public VFSFileService(VFSManager vfsManager) {
        this.vfsManager = vfsManager;
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, File file, InputStream fileContent) {
        return null;
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {
        return Stream.of();
    }

    @Override
    public File getFile(AuthIdentity identity, UUID entryId, String path) {
        return null;
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file, InputStream fileContent) {
        return null;
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {
        return null;
    }

    @Override
    public EntryItemId deleteFile(AuthIdentity identity, UUID entryId, String path) {
        return null;
    }
}
