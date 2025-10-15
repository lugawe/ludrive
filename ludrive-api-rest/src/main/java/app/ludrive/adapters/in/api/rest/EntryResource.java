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
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
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

        DriveUser driveUser = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.createEntry(driveUser, jsonEntry);

        return Response.ok(result).build();
    }

    @GET
    public Response getEntries() {

        DriveUser driveUser = restContextService.getAuthIdentity();

        Stream<JsonEntry> result = restEntryService.getEntries(driveUser);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}")
    public Response getEntry(@PathParam("entryId") UUID entryId) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.getEntry(driveUser, entryId);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{entryId}")
    public Response updateEntry(@PathParam("entryId") UUID entryId, JsonEntry jsonEntry) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        JsonEntry result = restEntryService.updateEntry(driveUser, entryId, jsonEntry);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{entryId}")
    public Response deleteEntry(@PathParam("entryId") UUID entryId) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        Entry result = restEntryService.deleteEntry(driveUser, entryId);
        if (result == null) {
            throw new NotFoundException();
        }

        return Response.noContent().build();
    }
}
