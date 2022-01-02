//package com.example.scrumfitapp.model;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.scrumfitapp.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class DailyAdapter extends BaseAdapter {
//
//
//    private Context context;
//    ArrayList<Workout> listOfWorkouts;
//
//
//    public DailyAdapter(Context context, ArrayList<Workout> listOfWorkouts) {
//        this.context = context;
//        this.listOfWorkouts = listOfWorkouts;
//    }
//
//    @Override
//    public int getCount() {
//        return listOfWorkouts.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return listOfWorkouts.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View oneDay;
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        oneDay = inflater.inflate(R.layout.item_daily_workout, viewGroup, false);
//
////        TextView tvTitle, tvTargetMuscles;
////
////        LayoutInflater inflater = LayoutInflater.from(context);
////        oneExercise = inflater.inflate(R.layout.item_exercise, viewGroup, false);
////
////        tvNoExercise = oneExercise.findViewById(R.id.tvNoExercise);
////        tvTargetMuscle = oneExercise.findViewById(R.id.edTargetMuscle);
////        tvExerciseName = oneExercise.findViewById(R.id.edExerciseName);
////        imgExercise = oneExercise.findViewById(R.id.imgExercise);
////
////        exercise = (Exercise) getItem(position);
////
////        tvNoExercise.setText((position + 1) + "");
////        tvTargetMuscle.setText(exercise.getBody_part());
////        tvExerciseName.setText(exercise.getName());
////
////        String animatedGifName = exercise.getImage();
////        Picasso
////                .with(this.context)
////                .load(animatedGifName)
////                .placeholder(R.drawable.noimage)
////                .into(imgExercise);
//        return oneDay;
//    }
//}