package app.ludrive.adapters.in.api.rest.json.converter;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.*;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.EntryItemId;
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

    public JsonEntryItemId toJsonEntryItemId(EntryItemId entryItemId) {

        JsonEntryItemId result = new JsonEntryItemId();

        result.setEntryId(entryItemId.entryId());
        result.setPath(entryItemId.path());

        return result;
    }

    public EntryItemId toEntryItemId(JsonEntryItemId entryItemId) {

        UUID entryId = entryItemId.getEntryId();
        String path = entryItemId.getPath();

        return new EntryItemId(entryId, path);
    }

    public JsonEntry toJsonEntry(Entry entry) {

        JsonEntry result = new JsonEntry();

        result.setId(entry.getId());
        result.setName(entry.getName());
        result.setDescription(entry.getDescription());

        return result;
    }

    public Entry toEntry(JsonEntry jsonEntry) {

        Entry result = new Entry();

        result.setId(jsonEntry.getId());
        result.setName(jsonEntry.getName());
        result.setDescription(jsonEntry.getDescription());

        return result;
    }

    public JsonDirectory toJsonDirectory(Directory directory) {

        JsonDirectory result = new JsonDirectory();

        result.setId(toJsonEntryItemId(directory.getId()));

        return result;
    }

    public Directory toDirectory(JsonDirectory directory) {

        EntryItemId entryItemId = toEntryItemId(directory.getId());

        return new Directory(entryItemId);
    }

    public JsonFile toJsonFile(File file) {

        JsonFile result = new JsonFile();

        result.setId(toJsonEntryItemId(file.getId()));

        return result;
    }

    public File toFile(JsonFile file) {

        EntryItemId entryItemId = toEntryItemId(file.getId());

        return new File(entryItemId);
    }
}
