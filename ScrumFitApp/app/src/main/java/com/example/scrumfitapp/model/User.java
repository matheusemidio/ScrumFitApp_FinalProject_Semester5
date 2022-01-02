package com.example.scrumfitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User
{
    private String username;
    private String birthday;
    private String email;
    private String height;
    private String name;
    private String password;
    private String weight;

    public User()
    {

    }

    public User( String username, String birthday, String email, String height, String name, String password, String weight)
    {
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.height = height;
        this.name = name;
        this.password = password;
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
