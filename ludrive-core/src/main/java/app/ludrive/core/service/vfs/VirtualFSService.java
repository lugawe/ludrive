package app.ludrive.core.service.vfs;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.exception.VFSException;

public interface VirtualFSService {

    void createDirectory(Directory directory) throws VFSException;

    void createFile(FileContent fileContent) throws VFSException;
}
