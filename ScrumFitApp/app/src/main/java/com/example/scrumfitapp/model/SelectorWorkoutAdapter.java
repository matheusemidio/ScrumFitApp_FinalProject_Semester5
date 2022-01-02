package com.example.scrumfitapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scrumfitapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SelectorWorkoutAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Workout> listOfWorkouts;

    ArrayList<Exercise> workout;

    public SelectorWorkoutAdapter(Context context, ArrayList<Workout> listOfWorkouts) {
        this.context = context;
        this.listOfWorkouts = listOfWorkouts;
    }

    @Override
    public int getCount() {
        return listOfWorkouts.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfWorkouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View dailyWorkout_view;
        TextView tvDayNumber, tvTargetMuscles;
        StringBuilder SB_targetMuscles = new StringBuilder();
        LayoutInflater inflater = LayoutInflater.from(context);
        ArrayList<String> listOfTargetMuscles = new ArrayList<String>();

        HashMap<String, ArrayList<Exercise>> exercises_map = listOfWorkouts.get(position);
        ArrayList<Exercise> listOfExercises = exercises_map.get("workout");
        //workout = listOfWorkouts.get(position).getWorkout();

        dailyWorkout_view = inflater.inflate(R.layout.item_daily_workout,  parent,false);
        tvDayNumber = dailyWorkout_view.findViewById(R.id.tvDayNumber);
        tvTargetMuscles = dailyWorkout_view.findViewById(R.id.tvTargetMuscles);


        for (HashMap<String, String> exercise : listOfExercises)
        {
            //listOfTargetMuscles.add(exercise.getBody_part());
            listOfTargetMuscles.add(exercise.get("body_part"));

        }

        //using a set to not have repetitive items
        Set<String> setOfTargetMuscles = new HashSet<String>(listOfTargetMuscles);
        listOfTargetMuscles.clear();
        listOfTargetMuscles.addAll(setOfTargetMuscles);

        //Adding the items to the String Builder
        for (String muscle : listOfTargetMuscles)
        {
            SB_targetMuscles.append(muscle).append(" | ");
        }

        //Setting the text views
        tvDayNumber.setText((position + 1) + "");
        tvTargetMuscles.setText(SB_targetMuscles.toString());

        return dailyWorkout_view;

    }
}
