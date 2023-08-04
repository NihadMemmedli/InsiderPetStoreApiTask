package org.insider.api.tests.delete;

import org.insider.api.base.BaseTest;
import org.insider.api.utils.PetTestUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetStoreDeleteNegativeTests extends BaseTest {
    @Test
    public void deleteNonExistentPet() {
        // Attempt to delete a pet with an ID that doesn't exist
        int nonExistentPetId = -1;

        given()
                .log().all()
                .when()
                .delete(baseUrl + nonExistentPetId)
                .then()
                .log().all()
                .statusCode(404); // Assuming that a 404 is returned for a non-existent pet
    }

    @Test
    public void deletePetTwice() {
        int petId = PetTestUtils.createAndReturnDefaultPetId();

        // Delete the pet once
        given().when().delete(baseUrl + petId).then().statusCode(200);

        // Try to delete the pet again
        given().when().delete(baseUrl + petId).then().statusCode(404); // Assuming 404 for not found
    }


}

