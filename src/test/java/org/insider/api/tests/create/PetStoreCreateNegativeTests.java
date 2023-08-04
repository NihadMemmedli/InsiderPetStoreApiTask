package org.insider.api.tests.create;

import org.insider.api.base.BaseTest;
import org.testng.annotations.Test;

import static org.insider.api.utils.PetTestUtils.performInvalidPetCreation;

public class PetStoreCreateNegativeTests extends BaseTest {

    @Test
    public void createPetWithInvalidId() {
        String invalidIdTypeJson = "{ \"id\": invalid, \"name\": \"doggie\", \"status\": \"available\" }";
        performInvalidPetCreation(invalidIdTypeJson);
    }

    @Test
    public void createPetWithMalformedJson() {
        String jsonBody = "{ \"id\": 1, \"name: \"doggie\", \"status\": \"available\" }"; // Missing quote
        performInvalidPetCreation(jsonBody);
    }

    @Test
    public void createPetWithMInvalidStatusType() {
        String jsonBody = "{ \"id\": 1, \"name: \"doggie\", \"status\": 1 }"; // Invalid Status Type
        performInvalidPetCreation(jsonBody);
    }

    @Test
    public void createPetWithMInvalidNameType() {
        String jsonBody = "{ \"id\": 1, \"name: 1, \"status\": 1 }"; // Invalid Name Type
        performInvalidPetCreation(jsonBody);
    }

}

