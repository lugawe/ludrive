package app.ludrive.adapters.in.api.rest;

import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.auth.AuthIdentityProvider;
import app.ludrive.adapters.in.api.rest.json.JsonEntryItem;
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
    @Path("/{entryId}")
    public Response createEntryItem(@PathParam("entryId") UUID entryId, JsonEntryItem jsonEntryItem) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntryItem result = restStorageService.createEntryItem(identity, entryId, jsonEntryItem);

        return Response.ok(result).build();
    }
}
