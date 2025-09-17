package app.ludrive.core.service.vfs;

import java.nio.channels.Channel;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;

public interface VirtualFSService {

    void createDirectory(Directory directory);

    void createFile(File file, Channel fileContent);
}
