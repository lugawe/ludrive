package app.ludrive.adapters.in.api.rest.service;

import java.io.InputStream;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.adapters.in.api.rest.json.converter.JsonConverter;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Content;
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

    public JsonDirectory createDirectory(AuthIdentity identity, UUID entryId, JsonDirectory jsonDirectory) {

        Directory directory = jsonConverter.toDirectory(jsonDirectory);

        Directory result = directoryServicePortIn.createDirectory(identity, entryId, directory);

        return jsonConverter.toJsonDirectory(result);
    }

    public JsonFile createFile(AuthIdentity identity, UUID entryId, JsonFile jsonFile, InputStream content) {

        File file = jsonConverter.toFile(jsonFile);

        File result = fileServicePortIn.createFile(identity, entryId, file, Content.from(content));

        return jsonConverter.toJsonFile(result);
    }
}
