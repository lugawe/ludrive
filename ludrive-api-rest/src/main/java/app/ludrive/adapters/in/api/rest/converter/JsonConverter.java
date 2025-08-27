package app.ludrive.adapters.in.api.rest.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.*;
import app.ludrive.core.domain.management.DriveUser;
import app.ludrive.core.domain.management.Entry;
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

        result.setEntryId(entryItemId.getEntryId());
        result.setPath(entryItemId.getPath());

        return result;
    }

    public EntryItemId toEntryItemId(JsonEntryItemId entryItemId) {

        EntryItemId result = new EntryItemId();

        result.setEntryId(entryItemId.getEntryId());
        result.setPath(entryItemId.getPath());

        return result;
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

        Directory result = new Directory();

        result.setId(toEntryItemId(directory.getId()));

        return result;
    }

    public JsonFile toJsonFile(File file) {

        JsonFile result = new JsonFile();

        result.setId(toJsonEntryItemId(file.getId()));

        return result;
    }

    public File toFile(JsonFile file) {

        File result = new File();

        result.setId(toEntryItemId(file.getId()));

        return result;
    }
}
