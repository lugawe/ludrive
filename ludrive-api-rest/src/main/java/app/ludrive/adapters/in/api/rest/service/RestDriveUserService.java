package app.ludrive.adapters.in.api.rest.service;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.converter.JsonConverter;
import app.ludrive.adapters.in.api.rest.json.JsonDriveUser;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.in.DriveUserServicePortIn;

@ApplicationScoped
public class RestDriveUserService {

    private final JsonConverter jsonConverter;
    private final DriveUserServicePortIn driveUserServicePortIn;

    @Inject
    public RestDriveUserService(JsonConverter jsonConverter, DriveUserServicePortIn driveUserServicePortIn) {
        this.jsonConverter = jsonConverter;
        this.driveUserServicePortIn = driveUserServicePortIn;
    }

    public JsonDriveUser createDriveUser(JsonDriveUser jsonDriveUser) {

        DriveUser driveUser = jsonConverter.toDriveUser(jsonDriveUser);

        DriveUser result = driveUserServicePortIn.createDriveUser(driveUser);

        return jsonConverter.toJsonDriveUser(result);
    }

    public JsonDriveUser getDriveUser(AuthIdentity identity, UUID driveUserId) {

        DriveUser driveUser = driveUserServicePortIn.getDriveUser(identity, driveUserId);

        return jsonConverter.toJsonDriveUser(driveUser);
    }

    public JsonDriveUser updateDriveUser(AuthIdentity identity, UUID driveUserId, JsonDriveUser jsonDriveUser) {

        DriveUser driveUser = jsonConverter.toDriveUser(jsonDriveUser);

        DriveUser result = driveUserServicePortIn.updateDriveUser(identity, driveUserId, driveUser);

        return jsonConverter.toJsonDriveUser(result);
    }

    public UUID deleteDriveUser(AuthIdentity identity, UUID driveUserId) {

        return driveUserServicePortIn.deleteDriveUser(identity, driveUserId);
    }
}
