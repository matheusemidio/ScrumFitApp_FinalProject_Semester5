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

public class ExerciseAdapter extends BaseAdapter {


    private Context context;
    ArrayList<Exercise> listOfExercises;

    Exercise exercise;
    public ExerciseAdapter(Context context, ArrayList<Exercise> listOfExercises) {
        this.context = context;
        this.listOfExercises = listOfExercises;
    }

    @Override
    public int getCount() {
        return listOfExercises.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfExercises.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View oneExercise;
        TextView tvNoExercise, tvTargetMuscle, tvExerciseName;
        ImageView imgExercise;

        LayoutInflater inflater = LayoutInflater.from(context);
        oneExercise = inflater.inflate(R.layout.item_exercise, viewGroup, false);

        tvNoExercise = oneExercise.findViewById(R.id.tvNoExercise);
        tvTargetMuscle = oneExercise.findViewById(R.id.edTargetMuscle);
        tvExerciseName = oneExercise.findViewById(R.id.edExerciseName);
        imgExercise = oneExercise.findViewById(R.id.imgExercise);

        //exercise = (Exercise) getItem(position);
        HashMap<String, String> exercise_map = listOfExercises.get(position);
        //exercise = listOfExercises.get(position);

        tvNoExercise.setText((position + 1) + "");
        tvTargetMuscle.setText(exercise_map.get("body_part"));
        tvExerciseName.setText(exercise_map.get("name"));


        String animatedGifName = exercise_map.get("image");
        Picasso
                .with(this.context)
                .load(animatedGifName)
                .placeholder(R.drawable.noimage)
                .into(imgExercise);
        return oneExercise;
    }
}
