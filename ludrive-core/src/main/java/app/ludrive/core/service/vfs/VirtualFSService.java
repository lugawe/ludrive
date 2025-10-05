package app.ludrive.core.service.vfs;

import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.domain.vfs.FileContent;
import app.ludrive.core.exception.VFSException;

public interface VirtualFSService {

    // --- vfs operations ---

    void createDirectory(Directory directory) throws VFSException;

    void updateDirectory(String path, Directory directory) throws VFSException;

    void deleteDirectory(String path) throws VFSException;

    void createFile(File file) throws VFSException;

    void updateFile(String path, File file) throws VFSException;

    void deleteFile(String path) throws VFSException;

    // --- vfs content operations ---

    void updateFileContent(String path, FileContent content) throws VFSException;

    FileContent getFileContent(String path) throws VFSException;
}
