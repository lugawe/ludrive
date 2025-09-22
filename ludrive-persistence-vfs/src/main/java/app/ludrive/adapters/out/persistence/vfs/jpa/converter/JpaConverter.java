package app.ludrive.adapters.out.persistence.vfs.jpa.converter;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaDirectory;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaEntryItemId;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaFile;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;

@ApplicationScoped
public class JpaConverter {

    @Inject
    public JpaConverter() {}

    public JpaEntryItemId toJpaEntryItemId(EntryItemId entryItemId) {

        JpaEntryItemId result = new JpaEntryItemId();

        result.setEntryId(entryItemId.entryId());
        result.setPath(entryItemId.path());

        return result;
    }

    public EntryItemId toEntryItemId(JpaEntryItemId entryItemId) {

        UUID entryId = entryItemId.getEntryId();
        String path = entryItemId.getPath();

        return new EntryItemId(entryId, path);
    }

    public JpaDirectory toJpaDirectory(Directory directory) {

        JpaDirectory result = new JpaDirectory();

        result.setId(toJpaEntryItemId(directory.getId()));

        return result;
    }

    public Directory toDirectory(JpaDirectory directory) {

        EntryItemId entryItemId = toEntryItemId(directory.getId());

        return new Directory(entryItemId);
    }

    public void updateJpaDirectory(JpaDirectory jpaDirectory, Directory directory) {}

    public JpaFile toJpaFile(File file) {

        JpaFile result = new JpaFile();

        result.setId(toJpaEntryItemId(file.getId()));

        return result;
    }

    public File toFile(JpaFile file) {

        EntryItemId entryItemId = toEntryItemId(file.getId());

        return new File(entryItemId);
    }

    public void updateJpaFile(JpaFile jpaFile, File file) {}
}
