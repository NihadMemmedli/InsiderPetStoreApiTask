package org.insider.api.tests.update;

import org.insider.api.base.BaseTest;
import org.insider.api.models.Pet;
import org.insider.api.models.PetBuilder;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class PetStoreUpdateNegativeTests extends BaseTest {

    @Test
    public void updatePetWithInvalidId() {
        Pet invalidIdPet = new PetBuilder().withId(-1) // Assuming negative IDs are invalid
                .withName("doggie")
                .withStatus("available")
                .build();

        given()
                .log().all()
                .contentType("application/json")
                .body(invalidIdPet)
                .when()
                .put(baseUrl)
                .then()
                .log().all()
                .statusCode(400); // Expected to return a 'Bad Request' status
    }
}



