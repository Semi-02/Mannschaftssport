package de.hsos.swa.mannschaftssport;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class TeamIdResourceTest {

    @Test
    void testGetTeamEndpoint() {
        given()
                .when().get("/teams/1")
                .then()
                .statusCode(200)
                .body("data.id", is("1"));
    }

    @Test
    void testUpdateTeamCategoryEndpoint() {
        given()
                .contentType("application/json")
                .body("{\"data\": {\"attributes\": {\"category\": \"seniors\"}}}")
                .when().patch("/teams/2")
                .then()
                .statusCode(200)
                .body("data.attributes.category", is("seniors"));
    }

    @Test
    void testDeleteTeamEndpoint() {
        given()
                .when().delete("/teams/3")
                .then()
                .statusCode(200);
    }
}