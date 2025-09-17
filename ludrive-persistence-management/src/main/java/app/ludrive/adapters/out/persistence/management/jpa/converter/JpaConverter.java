package app.ludrive.adapters.out.persistence.management.jpa.converter;

import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaDriveUser;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaEntry;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;

public final class JpaConverter {

    private JpaConverter() {}

    public static JpaDriveUser resolveAuthIdentity(AuthIdentity identity) {
        return toJpaDriveUser((DriveUser) identity);
    }

    public static JpaDriveUser toJpaDriveUser(DriveUser driveUser) {

        JpaDriveUser result = new JpaDriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public static DriveUser toDriveUser(JpaDriveUser driveUser) {

        DriveUser result = new DriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public static void updateJpaDriveUser(JpaDriveUser jpaDriveUser, DriveUser driveUser) {

        jpaDriveUser.setName(driveUser.getName());
    }

    public static JpaEntry toJpaEntry(Entry entry) {

        JpaEntry result = new JpaEntry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());

        return result;
    }

    public static Entry toEntry(JpaEntry entry) {

        Entry result = new Entry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());

        return result;
    }

    public static void updateJpaEntry(JpaEntry jpaEntry, Entry entry) {

        jpaEntry.setName(entry.getName());
        jpaEntry.setDescription(entry.getDescription());
    }
}
