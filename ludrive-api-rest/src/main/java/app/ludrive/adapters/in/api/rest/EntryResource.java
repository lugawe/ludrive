package app.ludrive.adapters.in.api.rest;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.auth.AuthIdentityProvider;
import app.ludrive.adapters.in.api.rest.json.JsonEntry;
import app.ludrive.adapters.in.api.rest.service.RestEntryService;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

@RolesAllowed({Roles.ROLE_DRIVE_ADMIN, Roles.ROLE_DRIVE_USER})
@Path("/entries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EntryResource {

    private final AuthIdentityProvider authIdentityProvider;
    private final RestEntryService restEntryService;

    @Inject
    public EntryResource(AuthIdentityProvider authIdentityProvider, RestEntryService restEntryService) {
        this.authIdentityProvider = authIdentityProvider;
        this.restEntryService = restEntryService;
    }

    @POST
    public Response createEntry(JsonEntry jsonEntry) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntry result = restEntryService.createEntry(identity, jsonEntry);

        return Response.ok(result).build();
    }

    @GET
    public Response getEntries() {

        AuthIdentity identity = authIdentityProvider.get();

        Stream<JsonEntry> result = restEntryService.getEntries(identity);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}")
    public Response getEntry(@PathParam("entryId") UUID entryId) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntry result = restEntryService.getEntry(identity, entryId);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{entryId}")
    public Response updateEntry(@PathParam("entryId") UUID entryId, JsonEntry jsonEntry) {

        AuthIdentity identity = authIdentityProvider.get();

        JsonEntry result = restEntryService.updateEntry(identity, entryId, jsonEntry);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{entryId}")
    public Response deleteEntry(@PathParam("entryId") UUID entryId) {

        AuthIdentity identity = authIdentityProvider.get();

        UUID result = restEntryService.deleteEntry(identity, entryId);
        if (result == null) {
            throw new NotFoundException();
        }

        return Response.noContent().build();
    }
}
