package app.ludrive.core.ports.out;

import java.io.InputStream;
import java.util.UUID;
import java.util.stream.Stream;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.repository.FileRepository;

public class DefaultFileServicePortOut implements FileServicePortOut {

    protected final Logger logger;
    protected final FileRepository fileRepository;

    public DefaultFileServicePortOut(Logger logger, FileRepository fileRepository) {
        this.logger = logger;
        this.fileRepository = fileRepository;
    }

    @Override
    public File createFile(AuthIdentity identity, UUID entryId, File file, InputStream fileContent) {

        return fileRepository.createFile(identity, entryId, file);
    }

    @Override
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {

        return fileRepository.getFiles(identity, entryId, path);
    }

    @Override
    public File getFile(AuthIdentity identity, UUID entryId, String path) {

        return fileRepository.getFile(identity, entryId, path);
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file, InputStream fileContent) {

        return fileRepository.updateFile(identity, entryId, path, file);
    }

    @Override
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        return fileRepository.updateFile(identity, entryId, path, file);
    }

    @Override
    public EntryItemId deleteFile(AuthIdentity identity, UUID entryId, String path) {

        return fileRepository.deleteFile(identity, entryId, path);
    }
}
