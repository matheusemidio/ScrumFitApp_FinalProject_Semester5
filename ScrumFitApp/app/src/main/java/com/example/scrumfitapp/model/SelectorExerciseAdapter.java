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


public class SelectorExerciseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Exercise_Model> listOfExercises;

    Exercise_Model exercise;

    public SelectorExerciseAdapter(Context context, ArrayList<Exercise_Model> listOfExercises) {
        this.context = context;
        this.listOfExercises = listOfExercises;
    }

    @Override
    public int getCount() {
        return listOfExercises.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfExercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //int IMAGE_REQUEST = 71;

        View vSelectExercise;
        TextView tvName;
        ImageView imAnimatedGif;

        LayoutInflater inflater = LayoutInflater.from(context);

        vSelectExercise = inflater.inflate(R.layout.item_exercise_display,  parent,false);

        tvName = vSelectExercise.findViewById(R.id.tvName);
        imAnimatedGif = vSelectExercise.findViewById(R.id.imAnimatedGif);

        exercise = (Exercise_Model)getItem(position);

        if(exercise.getName().equals(""))
        {
            //int animatedGifResourceId = context.getResources().getIdentifier("drawable/noimage", null,context.getPackageName());
            imAnimatedGif.setImageResource(R.drawable.noimage);
            tvName.setText("Exercise " + String.valueOf(position+ 1));
        }
        else
        {
            tvName.setText(exercise.getName());
            String animatedGifName = exercise.getImage();
            Picasso
                    .with(this.context)
                    .load(animatedGifName)
                    .placeholder(R.drawable.noimage)
                    .into(imAnimatedGif);
        }
        return vSelectExercise;
    }
}
