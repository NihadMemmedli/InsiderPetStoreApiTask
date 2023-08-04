package org.insider.api.tests.read;

import org.insider.api.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class PetStoreGetNegativeTests extends BaseTest {
    @Test
    public void getNonExistingPetById() {
        int petId = 9999999; // Assume this pet doesn't exist
        given()
                .log().all()
                .when()
                .get(baseUrl + "/" + petId)
                .then()
                .log().all()
                .statusCode(404); // Assuming a 404 Not Found response for a non-existing pet
    }

    @Test
    public void getPetWithMissingId() {
        given()
                .log().all()
                .when()
                .get(baseUrl + "/")
                .then()
                .log().all()
                .statusCode(404);
    }


}
