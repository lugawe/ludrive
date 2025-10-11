package app.ludrive.server.integration.adapters.in.api.rest;

import java.util.UUID;

import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.EntryResource;
import app.ludrive.adapters.in.api.rest.auth.Jwts;
import app.ludrive.adapters.in.api.rest.json.JsonEntry;
import app.ludrive.adapters.in.api.rest.json.JsonEntryConfiguration;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

@QuarkusTest
@TestHTTPEndpoint(EntryResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntryResourceTest {

    @Inject
    private DriveUserServicePortOut driveUserServicePortOut;

    private String jwt;

    private UUID entryId1;
    private UUID entryId2;

    public EntryResourceTest() {}

    private JsonEntryConfiguration jsonEntryConfiguration() {
        return new JsonEntryConfiguration("VFS2", "ram:///");
    }

    @BeforeAll
    public void createDriveUserAndJwt() {

        DriveUser driveUser = new DriveUser();
        driveUser.setName("Test User 1");

        DriveUser result = driveUserServicePortOut.createDriveUser(driveUser);

        jwt = Jwts.create(result);
    }

    @Test
    @Order(1)
    public void createEntry() {

        final String name1 = "test name 1";
        final String name2 = "test name 2";
        final String description1 = "test 1";
        final String description2 = "test 2";

        JsonEntry jsonEntry1 = new JsonEntry();
        jsonEntry1.setName(name1);
        jsonEntry1.setDescription(description1);
        jsonEntry1.setConfiguration(jsonEntryConfiguration());

        JsonEntry ret1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .body(jsonEntry1)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("name", Matchers.equalTo(name1))
                .body("description", Matchers.equalTo(description1))
                .extract()
                .as(JsonEntry.class);

        Assertions.assertNotNull(ret1);

        entryId1 = ret1.getId();

        JsonEntry jsonEntry2 = new JsonEntry();
        jsonEntry2.setName(name2);
        jsonEntry2.setDescription(description2);
        jsonEntry2.setConfiguration(jsonEntryConfiguration());

        JsonEntry ret2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .body(jsonEntry2)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("name", Matchers.equalTo(name2))
                .body("description", Matchers.equalTo(description2))
                .extract()
                .as(JsonEntry.class);

        Assertions.assertNotNull(ret2);

        entryId2 = ret2.getId();
    }

    @Test
    @Order(2)
    public void getEntries() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("", Matchers.hasSize(2));
    }

    @Test
    @Order(3)
    public void getEntry() {

        JsonEntry ret = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId1)
                .when()
                .get("/{entryId}")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("id", Matchers.equalTo(entryId1.toString()))
                .extract()
                .as(JsonEntry.class);

        Assertions.assertNotNull(ret);
    }

    @Test
    @Order(4)
    public void updateEntry() {

        JsonEntry update = new JsonEntry();
        update.setName("updated");
        update.setConfiguration(jsonEntryConfiguration());

        JsonEntry ret = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId1)
                .body(update)
                .when()
                .put("/{entryId}")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("name", Matchers.equalTo("updated"))
                .extract()
                .as(JsonEntry.class);

        Assertions.assertNotNull(ret);
    }

    @Test
    @Order(5)
    public void deleteEntry() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId2)
                .when()
                .delete("/{entryId}")
                .then()
                .statusCode(204);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("entryId", entryId2)
                .when()
                .get("/{entryId}")
                .then()
                .statusCode(404);
    }
}
