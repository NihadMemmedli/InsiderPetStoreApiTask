package org.insider.api.tests.read;

import org.insider.api.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetStoreGetPositiveTests extends BaseTest {

    @DataProvider(name = "statusProvider")
    public Object[][] statusProvider() {
        return new Object[][] {
                {"available"},
                {"pending"},
                {"sold"}
        };
    }
    @Test
    public void getExistingPetById() {
        int petId = 12345; // Assume this pet exists
        given()
                .log().all()
                .when()
                .get(baseUrl+ petId)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", notNullValue());
    }

    @Test(dataProvider = "statusProvider")
    public void getPetByValidStatus(String status) {
        given()
                .log().all()
                .when()
                .get(baseUrl + "findByStatus?status=" + status)
                .then()
                .log().all()
                .statusCode(200)
                .body("[0].status", equalTo(status));
    }

}

