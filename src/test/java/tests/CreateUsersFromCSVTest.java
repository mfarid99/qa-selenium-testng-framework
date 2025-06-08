package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class CreateUsersFromCSVTest {

    @DataProvider(name = "userData")
    public Iterator<Object[]> readUserDataFromCSV() throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/users.csv"));
        String line = reader.readLine(); // skip header

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(new Object[]{parts[0], parts[1]});
        }
        reader.close();
        return data.iterator();
    }

    @Test(dataProvider = "userData")
    public void createUserFromCSV(String name, String job) {
        RestAssured.baseURI = "https://reqres.in";

        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .body("{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }")
                .when()
                .post("/api/users")
                .then()
                .extract().response();

        System.out.println("Created User: " + response.asPrettyString());

        System.out.println("Created User: " + response.asPrettyString());

        assertEquals(response.statusCode(), 201);
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("createdAt")); // ✅ optional

    }
}
