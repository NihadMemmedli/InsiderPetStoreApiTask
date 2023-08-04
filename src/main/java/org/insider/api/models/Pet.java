package org.insider.api.models;

import java.util.List;

public class Pet {
    public long id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public List<Tag> tags;

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public String status;


    public static class Category {
        public long id;
        public String name;
    }

    public static class Tag {
        public long id;
        public String name;

        public Tag() {}

        public Tag(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }



}

