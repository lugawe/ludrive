package app.ludrive.adapters.in.api.rest.json.converter;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.*;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.EntryConfiguration;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;

@ApplicationScoped
public class JsonConverter {

    @Inject
    public JsonConverter() {}

    public JsonDriveUser toJsonDriveUser(DriveUser driveUser) {

        JsonDriveUser result = new JsonDriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public DriveUser toDriveUser(JsonDriveUser driveUser) {

        DriveUser result = new DriveUser();

        result.setId(driveUser.getId());
        result.setName(driveUser.getName());

        return result;
    }

    public JsonEntry toJsonEntry(Entry entry) {

        JsonEntry result = new JsonEntry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());
        result.setConfiguration(toJsonEntryConfiguration(entry.getConfiguration()));

        return result;
    }

    public Entry toEntry(JsonEntry jsonEntry) {

        Entry result = new Entry();

        result.setId(jsonEntry.getId());
        result.setName(jsonEntry.getName());
        result.setDescription(jsonEntry.getDescription());
        result.setConfiguration(toEntryConfiguration(jsonEntry.getConfiguration()));

        return result;
    }

    public JsonEntryConfiguration toJsonEntryConfiguration(EntryConfiguration entryConfiguration) {

        if (entryConfiguration == null) {
            return null;
        }

        JsonEntryConfiguration result = new JsonEntryConfiguration();

        result.setType(entryConfiguration.getType());
        result.setStoragePluginId(entryConfiguration.getStoragePluginId());
        result.setRootLocation(entryConfiguration.getRootLocation());

        Map<String, String> credentials = entryConfiguration.getCredentials();
        if (credentials != null) {
            result.setCredentials(new LinkedHashMap<>(credentials));
        }

        return result;
    }

    public EntryConfiguration toEntryConfiguration(JsonEntryConfiguration entryConfiguration) {

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

    public JsonDirectory toJsonDirectory(Directory directory) {

        JsonDirectory result = new JsonDirectory();

        result.setPath(directory.getPath());

        return result;
    }

    public Directory toDirectory(JsonDirectory directory) {

        String path = directory.getPath();

        return new Directory(path);
    }

    public JsonFile toJsonFile(File file) {

        JsonFile result = new JsonFile();

        result.setPath(file.getPath());

        return result;
    }

    public File toFile(JsonFile file) {

        String path = file.getPath();

        return new File(path);
    }
}
