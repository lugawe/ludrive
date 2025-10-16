package app.ludrive.adapters.in.api.rest;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import app.ludrive.adapters.in.api.rest.context.RestContextService;
import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonEntryItem;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.adapters.in.api.rest.service.RestStorageService;
import app.ludrive.core.domain.management.auth.DriveUser;
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
    @Path("/{entryId}/vfs/directories")
    public Response createDirectory(@PathParam("entryId") UUID entryId, @Valid JsonDirectory jsonDirectory) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        JsonDirectory result = restStorageService.createDirectory(driveUser, entryId, jsonDirectory);

        return Response.ok(result).build();
    }

    @POST
    @Path("/{entryId}/vfs/files")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createFile(
            @PathParam("entryId") UUID entryId,
            @RestForm("file") @PartType(MediaType.APPLICATION_JSON) @Valid JsonFile jsonFile,
            @RestForm("content") @PartType(MediaType.APPLICATION_OCTET_STREAM) InputStream content) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        JsonFile result = restStorageService.createFile(driveUser, entryId, jsonFile, content);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}/vfs")
    public Response getEntryItems(@PathParam("entryId") UUID entryId, @QueryParam("path") String path) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        List<? extends JsonEntryItem> result = restStorageService.getEntryItems(driveUser, entryId, path);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}/vfs/directories")
    public Response getDirectories(@PathParam("entryId") UUID entryId, @QueryParam("path") String path) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        List<JsonDirectory> result = restStorageService.getDirectories(driveUser, entryId, path);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{entryId}/vfs/files")
    public Response getFiles(@PathParam("entryId") UUID entryId, @QueryParam("path") String path) {

        DriveUser driveUser = restContextService.getAuthIdentity();

        List<JsonFile> result = restStorageService.getFiles(driveUser, entryId, path);

        return Response.ok(result).build();
    }
}
