package app.ludrive.adapters.in.api.rest;

import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.auth.AuthIdentityProvider;
import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonEntryItem;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.adapters.in.api.rest.service.RestStorageService;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

@RolesAllowed({Roles.ROLE_DRIVE_ADMIN, Roles.ROLE_DRIVE_USER})
@Path("/entries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StorageResource {

    private final AuthIdentityProvider authIdentityProvider;
    private final RestStorageService restStorageService;

    @Inject
    public StorageResource(AuthIdentityProvider authIdentityProvider, RestStorageService restStorageService) {
        this.authIdentityProvider = authIdentityProvider;
        this.restStorageService = restStorageService;
    }

    @POST
    @Path("/{entryId}/directories")
    public Response createDirectory(@PathParam("entryId") UUID entryId, JsonDirectory jsonDirectory) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntryItem result = restStorageService.createDirectory(identity, entryId, jsonDirectory);

        return Response.ok(result).build();
    }

    @POST
    @Path("/{entryId}/files")
    public Response createFile(@PathParam("entryId") UUID entryId, JsonFile jsonFile) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntryItem result = restStorageService.createFile(identity, entryId, jsonFile, null);

        return Response.ok(result).build();
    }
}
