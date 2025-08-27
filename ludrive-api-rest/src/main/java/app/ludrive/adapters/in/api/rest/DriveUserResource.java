package app.ludrive.adapters.in.api.rest;

import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.auth.AuthIdentityProvider;
import app.ludrive.adapters.in.api.rest.json.JsonDriveUser;
import app.ludrive.adapters.in.api.rest.service.RestDriveUserService;
import app.ludrive.core.domain.management.auth.AuthIdentity;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriveUserResource {

    private final AuthIdentityProvider authIdentityProvider;
    private final RestDriveUserService restDriveUserService;

    @Inject
    public DriveUserResource(AuthIdentityProvider authIdentityProvider, RestDriveUserService restDriveUserService) {
        this.authIdentityProvider = authIdentityProvider;
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

        AuthIdentity identity = authIdentityProvider.get();

        JsonDriveUser result = restDriveUserService.getDriveUser(identity, driveUserId);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{driveUserId}")
    public Response updateDriveUser(@PathParam("driveUserId") UUID driveUserId, JsonDriveUser jsonDriveUser) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonDriveUser result = restDriveUserService.updateDriveUser(identity, driveUserId, jsonDriveUser);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{driveUserId}")
    public Response deleteDriveUser(@PathParam("driveUserId") UUID driveUserId) {

        AuthIdentity identity = authIdentityProvider.get();

        UUID result = restDriveUserService.deleteDriveUser(identity, driveUserId);
        if (result == null) {
            throw new NotFoundException();
        }

        return Response.noContent().build();
    }
}
