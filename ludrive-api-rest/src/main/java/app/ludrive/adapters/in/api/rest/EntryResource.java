package app.ludrive.adapters.in.api.rest;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.context.RestContextService;
import app.ludrive.adapters.in.api.rest.json.JsonEntry;
import app.ludrive.adapters.in.api.rest.service.RestEntryService;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

@RolesAllowed({Roles.ROLE_DRIVE_ADMIN, Roles.ROLE_DRIVE_USER})
@Path("/entries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EntryResource {

    private final RestContextService restContextService;
    private final RestEntryService restEntryService;

    @Inject
    public EntryResource(RestContextService restContextService, RestEntryService restEntryService) {
        this.restContextService = restContextService;
        this.restEntryService = restEntryService;
    }

    @POST
    public Response createEntry(JsonEntry jsonEntry) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.createEntry(identity, jsonEntry);

        return Response.ok(result).build();
    }

    @GET
    public Response getEntries() {

        AuthIdentity identity = restContextService.getAuthIdentity();

        Stream<JsonEntry> result = restEntryService.getEntries(identity);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}")
    public Response getEntry(@PathParam("entryId") UUID entryId) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.getEntry(identity, entryId);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{entryId}")
    public Response updateEntry(@PathParam("entryId") UUID entryId, JsonEntry jsonEntry) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.updateEntry(identity, entryId, jsonEntry);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{entryId}")
    public Response deleteEntry(@PathParam("entryId") UUID entryId) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        UUID result = restEntryService.deleteEntry(identity, entryId);
        if (result == null) {
            throw new NotFoundException();
        }

        return Response.noContent().build();
    }
}
