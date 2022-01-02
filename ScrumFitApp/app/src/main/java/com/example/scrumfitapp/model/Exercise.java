package com.example.scrumfitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.HashMap;

public class Exercise extends HashMap<String, String> implements Serializable
{
    private String body_part;
    private String equipment;
    private String image;
    private String name;

    public Exercise()
    {

    }

    public Exercise(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public Exercise(String body_part, String equipment, String image, String name)
    {
        this.body_part = body_part;
        this.equipment = equipment;
        this.image = image;
        this.name = name;
    }

    public String getBody_part() {
        return body_part;
    }

    public void setBody_part(String body_part) {
        this.body_part = body_part;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString()
    {
        return this.getName();
    }
}
