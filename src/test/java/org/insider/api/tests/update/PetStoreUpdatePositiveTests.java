package org.insider.api.tests.update;

import org.insider.api.base.BaseTest;
import org.insider.api.models.Pet;
import org.insider.api.models.PetBuilder;
import org.insider.api.utils.PetTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreUpdatePositiveTests extends BaseTest {

    private Pet existingPet;

    @BeforeMethod
    public void setupPet() {
        existingPet = PetTestUtils.createDefaultPet();
        PetTestUtils.createPetViaApi(existingPet);
    }
    @Test
    public void updateExistingPet() {

        Pet updatedPet = new PetBuilder()
                .withId(existingPet.getId())
                .withName("newName")
                .withStatus("newStatus")
                .build();

        given()
                .log().all()
                .contentType("application/json")
                .body(updatedPet)
                .when()
                .put(baseUrl)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("newName"))
                .body("status", equalTo("newStatus"));
    }


    @Test
    public void updatePetWithAllFields() {

        // Update all the fields using the builder pattern
        Pet updatedPet = new PetBuilder()
                .withId(existingPet.getId())
                .withName("newName")
                .withStatus("newStatus")
                .withPhotoUrls("newUrl1", "newUrl2")
                // ... other fields
                .build();

        given()
                .log().all()
                .contentType("application/json")
                .body(updatedPet)
                .when()
                .put(baseUrl)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("newName"))
                .body("status", equalTo("newStatus"));
    }

}
