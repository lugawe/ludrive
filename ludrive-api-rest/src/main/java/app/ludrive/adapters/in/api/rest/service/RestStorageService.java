package app.ludrive.adapters.in.api.rest.service;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.converter.JsonConverter;
import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonEntryItem;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.ports.in.DirectoryServicePortIn;
import app.ludrive.core.ports.in.FileServicePortIn;

@ApplicationScoped
public class RestStorageService {

    private final JsonConverter jsonConverter;
    private final DirectoryServicePortIn directoryServicePortIn;
    private final FileServicePortIn fileServicePortIn;

    @Inject
    public RestStorageService(
            JsonConverter jsonConverter,
            DirectoryServicePortIn directoryServicePortIn,
            FileServicePortIn fileServicePortIn) {
        this.jsonConverter = jsonConverter;
        this.directoryServicePortIn = directoryServicePortIn;
        this.fileServicePortIn = fileServicePortIn;
    }

    public JsonEntryItem createEntryItem(AuthIdentity identity, UUID entryId, JsonEntryItem jsonEntryItem) {

        return switch (jsonEntryItem) {
            case JsonDirectory jsonDirectory -> {
                Directory directory = jsonConverter.toDirectory(jsonDirectory);
                Directory result = directoryServicePortIn.createDirectory(identity, entryId, directory);
                yield jsonConverter.toJsonDirectory(result);
            }
            case JsonFile jsonFile -> {
                File file = jsonConverter.toFile(jsonFile);
                File result = fileServicePortIn.createFile(identity, entryId, file, InputStream.nullInputStream());
                yield jsonConverter.toJsonFile(result);
            }
            default -> throw new IllegalStateException();
        };
    }
}
