package app.ludrive.adapters.out.persistence.vfs.fs;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;
import app.ludrive.core.service.vfs.VirtualFSService;

// TODO
public class VFS2Service implements VirtualFSService {

    public VFS2Service() {}

    @Override
    public void createDirectory(Directory directory) throws VFSException {}

    @Override
    public void updateDirectory(String path, Directory directory) throws VFSException {}

    @Override
    public void deleteDirectory(String path) throws VFSException {}

    @Override
    public void createFile(File file) throws VFSException {}

    @Override
    public void updateFile(String path, File file) throws VFSException {}

    @Override
    public void deleteFile(String path) throws VFSException {}

    @Override
    public void updateFileContent(String path, Content content) throws VFSException {}

    @Override
    public Content getFileContent(String path) throws VFSException {
        return null;
    }
}
