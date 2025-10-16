package app.ludrive.adapters.out.persistence.management.jpa.converter;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaDriveUser;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaEntry;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaEntryConfiguration;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.domain.management.auth.DriveUser;

@ApplicationScoped
public class JpaConverter {

    @Inject
    public JpaConverter() {}

    public JpaDriveUser toJpaDriveUser(DriveUser driveUser) {

        JpaDriveUser result = new JpaDriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public DriveUser toDriveUser(JpaDriveUser driveUser) {

        DriveUser result = new DriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public void updateJpaDriveUser(JpaDriveUser jpaDriveUser, DriveUser driveUser) {

        jpaDriveUser.setName(driveUser.getName());
    }

    public JpaEntry toJpaEntry(Entry entry) {

        JpaEntry result = new JpaEntry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());
        result.setConfiguration(toJpaEntryConfiguration(entry.getConfiguration()));

        return result;
    }

    public Entry toEntry(JpaEntry entry) {

        Entry result = new Entry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());
        result.setConfiguration(toEntryConfiguration(entry.getConfiguration()));

        return result;
    }

    public void updateJpaEntry(JpaEntry jpaEntry, Entry entry) {

        jpaEntry.setName(entry.getName());
        jpaEntry.setDescription(entry.getDescription());
        jpaEntry.setConfiguration(toJpaEntryConfiguration(entry.getConfiguration()));
    }

    public JpaEntryConfiguration toJpaEntryConfiguration(EntryConfiguration entryConfiguration) {

        if (entryConfiguration == null) {
            return null;
        }

        JpaEntryConfiguration result = new JpaEntryConfiguration();

        result.setType(entryConfiguration.getType());
        result.setStoragePluginId(entryConfiguration.getStoragePluginId());
        result.setRootLocation(entryConfiguration.getRootLocation());

        Map<String, String> credentials = entryConfiguration.getCredentials();
        if (credentials != null) {
            result.setCredentials(new LinkedHashMap<>(credentials));
        }

        return result;
    }

    public EntryConfiguration toEntryConfiguration(JpaEntryConfiguration entryConfiguration) {

        if (entryConfiguration == null) {
            return null;
        }

        EntryConfiguration result = new EntryConfiguration();

        result.setType(entryConfiguration.getType());
        result.setStoragePluginId(entryConfiguration.getStoragePluginId());
        result.setRootLocation(entryConfiguration.getRootLocation());

        Map<String, String> credentials = entryConfiguration.getCredentials();
        if (credentials != null) {
            result.setCredentials(new LinkedHashMap<>(credentials));
        }

        return result;
    }
}
