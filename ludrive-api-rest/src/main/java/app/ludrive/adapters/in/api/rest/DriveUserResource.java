package app.ludrive.adapters.in.api.rest;

import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.context.RestContextService;
import app.ludrive.adapters.in.api.rest.json.JsonDriveUser;
import app.ludrive.adapters.in.api.rest.service.RestDriveUserService;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.DriveUser;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriveUserResource {

    private final RestContextService restContextService;
    private final RestDriveUserService restDriveUserService;

    @Inject
    public DriveUserResource(RestContextService restContextService, RestDriveUserService restDriveUserService) {
        this.restContextService = restContextService;
        this.restDriveUserService = restDriveUserService;
    }

    @POST
    public Response createDriveUser(JsonDriveUser jsonDriveUser) {

        JsonDriveUser result = restDriveUserService.createDriveUser(jsonDriveUser);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{driveUserId}")
    public Response getDriveUser(@PathParam("driveUserId") UUID driveUserId) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonDriveUser result = restDriveUserService.getDriveUser(identity, driveUserId);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{driveUserId}")
    public Response updateDriveUser(@PathParam("driveUserId") UUID driveUserId, JsonDriveUser jsonDriveUser) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonDriveUser result = restDriveUserService.updateDriveUser(identity, driveUserId, jsonDriveUser);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{driveUserId}")
    public Response deleteDriveUser(@PathParam("driveUserId") UUID driveUserId) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        DriveUser result = restDriveUserService.deleteDriveUser(identity, driveUserId);
        if (result == null) {
            throw new NotFoundException();
        }

        return Response.noContent().build();
    }
}
