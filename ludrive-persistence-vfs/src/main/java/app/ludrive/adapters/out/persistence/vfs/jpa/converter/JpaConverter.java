package app.ludrive.adapters.out.persistence.vfs.jpa.converter;

import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaDirectory;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaEntryItemId;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaFile;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
import app.ludrive.core.domain.vfs.File;

public final class JpaConverter {

    private JpaConverter() {}

    public static JpaEntryItemId toJpaEntryItemId(EntryItemId entryItemId) {

        JpaEntryItemId result = new JpaEntryItemId();

        result.setEntryId(entryItemId.getEntryId());
        result.setPath(entryItemId.getPath());

        return result;
    }

    public static EntryItemId toEntryItemId(JpaEntryItemId entryItemId) {

        EntryItemId result = new EntryItemId();

        result.setEntryId(entryItemId.getEntryId());
        result.setPath(entryItemId.getPath());

        return result;
    }

    public static JpaDirectory toJpaDirectory(Directory directory) {

        JpaDirectory result = new JpaDirectory();

        result.setId(toJpaEntryItemId(directory.getId()));

        return result;
    }

    public static Directory toDirectory(JpaDirectory directory) {

        Directory result = new Directory();

        result.setId(toEntryItemId(directory.getId()));

        return result;
    }

    public static void updateJpaDirectory(JpaDirectory jpaDirectory, Directory directory) {}

    public static JpaFile toJpaFile(File file) {

        JpaFile result = new JpaFile();

        result.setId(toJpaEntryItemId(file.getId()));

        return result;
    }

    public static File toFile(JpaFile file) {

        File result = new File();

        result.setId(toEntryItemId(file.getId()));

        return result;
    }

    public static void updateJpaFile(JpaFile jpaFile, File file) {}
}
