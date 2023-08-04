package org.insider.api.tests.e2e;

import org.insider.api.base.BaseTest;
import org.insider.api.models.Pet;
import org.insider.api.models.PetBuilder;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreEnd2EndTest extends BaseTest {
    // End to End positive
    @Test
    public void crudPetTest() throws InterruptedException {
        // C: Create a new pet and return its ID
        Pet newPet = new PetBuilder()
                .withName("Fido")
                .withStatus("available")
                // ... other fields
                .build();

        Long petId =
                given()
                        .contentType("application/json")
                        .body(newPet)
                        .when()
                        .post(baseUrl)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        // R: Read the pet and verify its details
        given()
                .pathParam("id", petId)
                .when()
                .log().all()
                .get(baseUrl + "{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("Fido"))
                .body("status", equalTo("available"));

        // U: Update the pet and verify the update
        Pet updatedPet = new PetBuilder()
                .withId(petId)
                .withName("Buddy")
                .withStatus("sold")
                .build();

        given()
                .contentType("application/json")
                .body(updatedPet)
                .when()
                .put(baseUrl)
                .then()
                .statusCode(200)
                .body("name", equalTo("Buddy"))
                .body("status", equalTo("sold"));

        // D: Delete the pet and verify deletion
        given()
                .pathParam("id", petId)
                .when()
                .delete(baseUrl + "{id}")
                .then()
                .statusCode(200);

        // Verify that the pet is no longer available
        given()
                .pathParam("id", petId)
                .when()
                .get(baseUrl + "{id}")
                .then()
                .statusCode(404);
    }

}
