package app.ludrive.core.service.vfs;

import java.nio.channels.Channel;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;

public interface VirtualFSService {

    void createDirectory(Directory directory) throws VFSException;

    void createFile(File file, Channel fileContent) throws VFSException;
}
