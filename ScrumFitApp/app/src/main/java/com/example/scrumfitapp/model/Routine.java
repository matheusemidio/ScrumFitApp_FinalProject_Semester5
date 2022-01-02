package com.example.scrumfitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Routine
{
    private String username;
    private String number_of_days;
    private ArrayList<Workout> listOfWorkouts;

    public Routine()
    {

    }

    public Routine(String username, String number_of_days, ArrayList<Workout> listOfWorkouts)
    {
        this.username = username;
        this.number_of_days = number_of_days;
        this.listOfWorkouts = listOfWorkouts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(String number_of_days) {
        this.number_of_days = number_of_days;
    }

    public ArrayList<Workout> getListOfWorkouts() {
        return listOfWorkouts;
    }

    public void setListOfWorkouts(ArrayList<Workout> listOfWorkouts) {
        this.listOfWorkouts = listOfWorkouts;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
