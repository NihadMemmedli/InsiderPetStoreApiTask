package org.insider.api.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetBuilder {
    private final Pet pet;

    public PetBuilder() {
        this.pet = new Pet();
    }

    public PetBuilder withId(long id) {
        pet.id = id;
        return this;
    }

    public PetBuilder withName(String name) {
        pet.name = name;
        return this;
    }

    public PetBuilder withStatus(String status) {
        pet.status = status;
        return this;
    }

    public PetBuilder withPhotoUrls(String... photoUrls) {
        pet.photoUrls = Arrays.asList(photoUrls);
        return this;
    }

    public PetBuilder withCategory(long id, String name) {
        pet.category = new Pet.Category();
        pet.category.id = id;
        pet.category.name = name;
        return this;
    }

    public PetBuilder withTags(Pet.Tag... tags) {
        pet.tags = Arrays.asList(tags);
        return this;
    }

    public PetBuilder withTag(long id, String name) {
        if (pet.tags == null) {
            pet.tags = new ArrayList<>();
        }
        pet.tags.add(new Pet.Tag(id, name));
        return this;
    }

    public Pet build() {
        return pet;
    }
}
