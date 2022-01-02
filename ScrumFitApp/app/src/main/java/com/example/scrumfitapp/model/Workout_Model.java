package com.example.scrumfitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Workout_Model {
    private String id;
    private ArrayList<Exercise_Model> workout;

    public Workout_Model()
    {

    }

    public Workout_Model(String id, ArrayList<Exercise_Model> workout)
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

    public ArrayList<Exercise_Model> getWorkout() {
        return workout;
    }

    public void setWorkout(ArrayList<Exercise_Model> workout) {
        this.workout = workout;
    }

    public void setExercise(Exercise_Model exercise)
    {
        workout.add(exercise);
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
        for (Exercise_Model exercise:workout)
        {
            sb.append(exercise.toString() + " | ");
        }

        return sb.toString();
    }
}