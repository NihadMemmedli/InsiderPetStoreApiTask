package org.insider.api.tests.delete;

import org.insider.api.base.BaseTest;
import org.insider.api.models.Pet;
import org.insider.api.utils.PetTestUtils;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class PetStoreDeletePositiveTests extends BaseTest {
    @Test
    public void deleteExistingPet() {
        // First, create a pet that you can delete
        Pet existingPet = PetTestUtils.createDefaultPet();
        PetTestUtils.createPetViaApi(existingPet);

        given()
                .log().all()
                .when()
                .delete(baseUrl + existingPet.getId())
                .then()
                .log().all()
                .statusCode(200);

        // Optionally, you could also add a step to try to fetch the deleted pet to make sure it's gone
        given()
                .log().all()
                .when()
                .get(baseUrl + existingPet.getId())
                .then()
                .log().all()
                .statusCode(404); // Assuming that a 404 is returned for a non-existent pet
    }

}
