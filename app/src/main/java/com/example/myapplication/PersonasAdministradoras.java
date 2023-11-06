package com.example.myapplication;

public class PersonasAdministradoras {
    private String name;
    private int imageResId;
    private String description;

    public PersonasAdministradoras(String name, int imageResId, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
