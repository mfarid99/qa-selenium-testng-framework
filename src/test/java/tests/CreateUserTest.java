package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CreateUserTest {

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = "https://reqres.in";

        String payload = """
            {
                "name": "nayah",
                "job": "qa engineer"
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")  // ✅ include your API key here
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();

        System.out.println("Response:\n" + response.asPrettyString());

        // Assertions
        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("name"), "nayah");
        assertEquals(response.jsonPath().getString("job"), "qa engineer");
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("createdAt"));
    }
}
