package app.ludrive.server.integration.adapters.in.api.rest;

import java.util.UUID;

import app.ludrive.adapters.in.api.rest.DriveUserResource;
import app.ludrive.adapters.in.api.rest.auth.Jwts;
import app.ludrive.adapters.in.api.rest.json.JsonDriveUser;
import app.ludrive.core.domain.management.auth.DriveUser;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

@QuarkusTest
@TestHTTPEndpoint(DriveUserResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriveUserResourceTest {

    private UUID driveUserId;

    private String jwt;

    @Test
    @Order(1)
    public void createDriveUser() {

        JsonDriveUser jsonDriveUser = new JsonDriveUser();
        jsonDriveUser.setName("test user 1");

        JsonDriveUser ret1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonDriveUser)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("name", Matchers.equalTo("test user 1"))
                .extract()
                .as(JsonDriveUser.class);

        Assertions.assertNotNull(ret1);

        driveUserId = ret1.getId();

        DriveUser driveUser = new DriveUser();
        driveUser.setId(ret1.getId());
        driveUser.setName(ret1.getName());

        jwt = Jwts.create(driveUser);
    }

    @Test
    @Order(2)
    public void getDriveUser() {

        JsonDriveUser ret = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("driveUserId", driveUserId)
                .when()
                .get("/{driveUserId}")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("id", Matchers.equalTo(driveUserId.toString()))
                .extract()
                .as(JsonDriveUser.class);

        Assertions.assertNotNull(ret);
    }

    @Test
    @Order(3)
    public void updateDriveUser() {

        JsonDriveUser update = new JsonDriveUser();
        update.setName("updated");

        JsonDriveUser ret = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("driveUserId", driveUserId)
                .body(update)
                .when()
                .put("/{driveUserId}")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.emptyString()))
                .body("name", Matchers.equalTo("updated"))
                .extract()
                .as(JsonDriveUser.class);

        Assertions.assertNotNull(ret);
    }

    @Test
    @Order(4)
    public void deleteDriveUser() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("driveUserId", driveUserId)
                .when()
                .delete("/{driveUserId}")
                .then()
                .statusCode(204);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(jwt)
                .pathParam("driveUserId", driveUserId)
                .when()
                .get("/{driveUserId}")
                .then()
                .statusCode(404);
    }
}
