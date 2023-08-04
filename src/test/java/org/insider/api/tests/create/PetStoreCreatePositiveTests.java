package org.insider.api.tests.create;

import org.insider.api.base.BaseTest;
import org.insider.api.models.Pet;
import org.insider.api.models.PetBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.insider.api.utils.PetTestUtils.createPetViaApi;

public class PetStoreCreatePositiveTests extends BaseTest {
    @Test
    public void createPetWithRequiredFields() {
        Pet pet = new PetBuilder()
                .withId(0)
                .withName("doggie")
                .withStatus("available")
                .withPhotoUrls("url1", "url2", "url3")
                .withCategory(0, "string")
                .withTag(0, "string")
                .build();

        createPetViaApi(pet)
                .body("name", equalTo("doggie"))
                .body("status", equalTo("available"))
                .body("photoUrls", hasItems("url1", "url2", "url3"))
                .body("tags[0].name", equalTo("string"))
                .body("category.name", equalTo("string"));
    }

    @Test
    public void createPetWithAllFields() {
        Pet pet = new PetBuilder()
                .withId(12345)
                .withName("fluffy")
                .withStatus("available")
                .withPhotoUrls("url1", "url2")
                .withCategory(1, "cat")
                .withTags(new Pet.Tag(1, "tag1"), new Pet.Tag(2, "tag2"))
                .build();

        createPetViaApi(pet)
                .body("id", equalTo(12345))
                .body("name", equalTo("fluffy"))
                .body("status", equalTo("available"))
                .body("photoUrls", hasItems("url1", "url2"))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("cat"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("tag1"))
                .body("tags[1].id", equalTo(2))
                .body("tags[1].name", equalTo("tag2"));
    }

    @Test
    public void createPetWithDifferentStatuses() {
        for (String status : Arrays.asList("available", "pending", "sold")) {
            Pet pet = new PetBuilder()
                    .withName("fluffy")
                    .withStatus(status)
                    .build();

            createPetViaApi(pet)
                    .body("status", equalTo(status));
        }
    }
}
