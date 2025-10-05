package app.ludrive.server.integration.adapters.in.api.rest;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.StorageResource;
import app.ludrive.adapters.in.api.rest.auth.Jwts;
import app.ludrive.adapters.in.api.rest.json.JsonDirectory;
import app.ludrive.adapters.in.api.rest.json.JsonFile;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.ports.out.EntryServicePortOut;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

@QuarkusTest
@TestHTTPEndpoint(StorageResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StorageResourceTest {

    @Inject
    private DriveUserServicePortOut driveUserServicePortOut;

    @Inject
    private EntryServicePortOut entryServicePortOut;

    private String jwt;

    private UUID entryId;

    public StorageResourceTest() {}

    @BeforeAll
    public void createDriveUserAndEntryAndJwt() {

        DriveUser driveUser = new DriveUser();
        driveUser.setName("Test User 2");

        DriveUser createdDriveUser = driveUserServicePortOut.createDriveUser(driveUser);

        Entry entry = new Entry();
        entry.setName("Test Entry 1 Name");
        entry.setDescription("Test Entry 1 Description");

        Entry createdEntry = entryServicePortOut.createEntry(createdDriveUser, entry);

        entryId = createdEntry.getId();

        jwt = Jwts.create(createdDriveUser);
    }

    @Test
    @Order(1)
    public void createDirectory() {

        JsonDirectory jsonDirectory1 = new JsonDirectory(entryId, "/dir0");

        JsonDirectory ret1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId)
                .body(jsonDirectory1)
                .when()
                .post("/{entryId}/directories")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .extract()
                .as(JsonDirectory.class);

        Assertions.assertNotNull(ret1);

        JsonDirectory jsonDirectory2 = new JsonDirectory(entryId, "/dir1");

        JsonDirectory ret2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId)
                .body(jsonDirectory2)
                .when()
                .post("/{entryId}/directories")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .extract()
                .as(JsonDirectory.class);

        Assertions.assertNotNull(ret2);
    }

    @Test
    @Order(2)
    public void createFile() {

        JsonFile jsonFile = new JsonFile(entryId, "/dir1/hello.txt");

        JsonFile ret1 = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId)
                .multiPart("file", jsonFile, "application/json")
                .multiPart("content", "", "Hello World!".getBytes(StandardCharsets.UTF_8), "application/octet-stream")
                .when()
                .post("/{entryId}/files")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .extract()
                .as(JsonFile.class);

        Assertions.assertNotNull(ret1);
    }
}
