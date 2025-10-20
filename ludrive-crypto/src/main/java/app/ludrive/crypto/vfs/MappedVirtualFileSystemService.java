package app.ludrive.crypto.vfs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import app.ludrive.core.domain.vfs.Content;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.VFSException;
import app.ludrive.core.service.vfs.VirtualFileSystemService;
import app.ludrive.core.service.vfs.VirtualFileSystemTree;

public class MappedVirtualFileSystemService implements VirtualFileSystemService {

    protected final Map<String, String> pathMapping = new HashMap<>();

    protected final VirtualFileSystemService virtualFileSystemService;

    public MappedVirtualFileSystemService(VirtualFileSystemService virtualFileSystemService) {
        this.virtualFileSystemService = virtualFileSystemService;
    }

    protected String calculatePathMapping(String path) {
        // TODO should always return same for same path - secure hash?
        return UUID.randomUUID().toString();
    }

    protected String convertPath(String path) {
        return pathMapping.computeIfAbsent(path, this::calculatePathMapping);
    }

    protected Directory convertDirectory(Directory directory) {
        return new Directory(convertPath(directory.getPath()));
    }

    protected File convertFile(File file) {
        return new File(convertPath(file.getPath()));
    }

    @Override
    public void initialize(VirtualFileSystemTree tree) {
        virtualFileSystemService.initialize(tree);
    }

    @Override
    public void createDirectory(Directory directory) throws VFSException {

        Directory mappedDirectory = convertDirectory(directory);

        virtualFileSystemService.createDirectory(mappedDirectory);
    }

    @Override
    public void updateDirectory(String path, Directory directory) throws VFSException {

        // TODO check items are moved correctly

        String mappedPath = convertPath(path);
        Directory mappedDirectory = convertDirectory(directory);

        virtualFileSystemService.updateDirectory(mappedPath, mappedDirectory);
    }

    @Override
    public void deleteDirectory(String path) throws VFSException {

        // TODO check items are deleted correctly

        String mappedPath = convertPath(path);

        virtualFileSystemService.deleteDirectory(mappedPath);
    }

    @Override
    public void createFile(File file) throws VFSException {

        File mappedFile = convertFile(file);

        virtualFileSystemService.createFile(mappedFile);
    }

    @Override
    public void updateFile(String path, File file) throws VFSException {

        String mappedPath = convertPath(path);
        File mappedFile = convertFile(file);

        virtualFileSystemService.updateFile(mappedPath, mappedFile);
    }

    @Override
    public void deleteFile(String path) throws VFSException {

        String mappedPath = convertPath(path);

        virtualFileSystemService.deleteFile(mappedPath);
    }

    @Override
    public void updateFileContent(String path, Content content) throws VFSException {

        String mappedPath = convertPath(path);

        virtualFileSystemService.updateFileContent(mappedPath, content);
    }

    @Override
    public Content getFileContent(String path) throws VFSException {

        String mappedPath = convertPath(path);

        return virtualFileSystemService.getFileContent(mappedPath);
    }
}
