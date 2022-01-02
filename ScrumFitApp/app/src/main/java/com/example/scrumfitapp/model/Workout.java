package com.example.scrumfitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Workout extends HashMap<String, ArrayList<Exercise>> {
    private String id;
    private ArrayList<Exercise> workout;

    public Workout()
    {

    }

    public Workout(String id, ArrayList<Exercise> workout)
    {
        this.id = id;
        this.workout = workout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Exercise> getWorkout() {
        return workout;
    }

    public void setWorkout(ArrayList<Exercise> workout) {
        this.workout = workout;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        //This method will return all the exercises on this workout
        StringBuilder sb = new StringBuilder();
        for (Exercise exercise:workout)
        {
            sb.append(exercise.toString() + " | ");
        }

        return sb.toString();
    }
}
