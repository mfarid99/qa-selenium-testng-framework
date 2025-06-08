package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;

public class UserApiTest {

    @Test
    public void testGetSingleUser() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when()
                .get("/api/users/2")
                .then()
                .extract()
                .response();

        System.out.println("Response body:\n" + response.asPrettyString());
        System.out.println(response.statusCode());
        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
    }
}
