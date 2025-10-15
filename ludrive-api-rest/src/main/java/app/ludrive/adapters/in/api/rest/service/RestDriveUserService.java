package app.ludrive.adapters.in.api.rest.service;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.json.JsonDriveUser;
import app.ludrive.adapters.in.api.rest.json.converter.JsonConverter;
import app.ludrive.core.domain.management.auth.AnonymousAuthIdentity;
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

        DriveUser result = driveUserServicePortIn.createDriveUser(AnonymousAuthIdentity.getInstance(), driveUser);

        return jsonConverter.toJsonDriveUser(result);
    }

    public JsonDriveUser getDriveUser(DriveUser driveUser, UUID driveUserId) {

        DriveUser result = driveUserServicePortIn.getDriveUser(driveUser, driveUserId);

        return jsonConverter.toJsonDriveUser(result);
    }

    public JsonDriveUser updateDriveUser(DriveUser driveUser, UUID driveUserId, JsonDriveUser jsonDriveUser) {

        DriveUser updatedDriveUser = jsonConverter.toDriveUser(jsonDriveUser);

        DriveUser result = driveUserServicePortIn.updateDriveUser(driveUser, driveUserId, updatedDriveUser);

        return jsonConverter.toJsonDriveUser(result);
    }

    public DriveUser deleteDriveUser(DriveUser driveUser, UUID driveUserId) {

        return driveUserServicePortIn.deleteDriveUser(driveUser, driveUserId);
    }
}
