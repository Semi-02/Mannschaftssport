package de.hsos.swa.mannschaftssport;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class TeamResourceTest {

    @Test
    void testGetTeamsEndpoint() {
        given()
                .when().get("/teams")
                .then()
                .statusCode(200)
                .body("data", notNullValue());
    }

    @Test
    void testCreateTeamEndpoint() {
        given()
                .contentType("application/json")
                .body("{\"type\": \"teams\", \"attributes\": {\"name\": \"BC BW Ermke\", \"category\": \"masters\"}}")
                .when().post("/teams")
                .then()
                .statusCode(201)
                .body("data.attributes.name", is("BC BW Ermke"))
                .body("data.attributes.category", is("masters"));
    }
}