package app.ludrive.adapters.out.persistence.vfs.fs;

import java.nio.channels.Channel;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.service.validation.Validator;
import app.ludrive.core.service.vfs.VirtualFSService;

public class VFS2Service implements VirtualFSService {

    private final Validator validator;

    public VFS2Service(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void createDirectory(Directory directory) {
        validator.validateDirectory(directory);
    }

    @Override
    public void createFile(File file, Channel fileContent) {
        validator.validateFile(file, fileContent);
    }
}
