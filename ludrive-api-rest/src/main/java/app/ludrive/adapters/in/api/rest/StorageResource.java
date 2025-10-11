package app.ludrive.adapters.in.api.rest;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.context.RestContextService;
import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonEntryItem;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.adapters.in.api.rest.service.RestStorageService;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

@RolesAllowed({Roles.ROLE_DRIVE_ADMIN, Roles.ROLE_DRIVE_USER})
@Path("/entries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StorageResource {

    private final RestContextService restContextService;
    private final RestStorageService restStorageService;

    @Inject
    public StorageResource(RestContextService restContextService, RestStorageService restStorageService) {
        this.restContextService = restContextService;
        this.restStorageService = restStorageService;
    }

    @POST
    @Path("/{entryId}/directories")
    public Response createDirectory(@PathParam("entryId") UUID entryId, JsonDirectory jsonDirectory) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonDirectory result = restStorageService.createDirectory(identity, entryId, jsonDirectory);

        return Response.ok(result).build();
    }

    @POST
    @Path("/{entryId}/files")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createFile(
            @PathParam("entryId") UUID entryId,
            @RestForm("file") @PartType(MediaType.APPLICATION_JSON) JsonFile jsonFile,
            @RestForm("content") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream content) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        JsonFile result = restStorageService.createFile(identity, entryId, jsonFile, content);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}/vfs")
    public Response getEntryItems(@PathParam("entryId") UUID entryId, @QueryParam("path") String path) {

        AuthIdentity identity = restContextService.getAuthIdentity();

        List<? extends JsonEntryItem> result = restStorageService.getEntryItems(identity, entryId, path);

        return Response.ok(result).build();
    }
}
