package org.insider.api.utils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.insider.api.models.Pet;
import org.insider.api.models.PetBuilder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetTestUtils {
    private static final String baseUrl = "https://petstore.swagger.io/v2/pet";

    public static Pet createDefaultPet() {
        return new PetBuilder()
                .withId(10)
                .withName("fluffy")
                .withStatus("available")
                .withPhotoUrls("url1", "url2")
                .build();
    }

    public static ValidatableResponse createPetViaApi(Pet pet) {
        return given()
                .log().all()
                .contentType("application/json")
                .body(pet)
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void performInvalidPetCreation(String jsonBody) {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("bad input"));
    }

    public static int createAndReturnDefaultPetId() {
        Pet defaultPet = PetTestUtils.createDefaultPet();

        return given()
                .contentType("application/json")
                .body(defaultPet)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

}
