package com.example.scrumfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrumfitapp.model.Exercise;
import com.example.scrumfitapp.model.ExerciseAdapter;
import com.example.scrumfitapp.model.GlobalUser;
import com.example.scrumfitapp.model.Workout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ListRoutine_Workout_Activity extends AppCompatActivity implements View.OnClickListener, ValueEventListener, AdapterView.OnItemClickListener {

    //Declaration of views
    Button btnHome, btnListRoutine, btnCreateRoutine, btnProfile;
    ListView lvExercises;
    ExerciseAdapter exerciseAdapter;
    String position_data;
    Workout listOfExercises;
    ArrayList<Workout> listOfWorkouts;
    HashMap mapOfWorkouts;

    Exercise currentExercise;

    DatabaseReference routineDatabase, routineChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_routine_workout);

        initialize();

    }

    private void initialize()
    {
        //Initialization of views
        lvExercises = findViewById(R.id.lvExercises);
        btnHome = findViewById(R.id.btnHome);
        btnListRoutine = findViewById(R.id.btnListRoutine);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome.setOnClickListener(this);
        btnListRoutine.setOnClickListener(this);
        btnCreateRoutine.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        lvExercises.setOnItemClickListener(this);

        routineDatabase = FirebaseDatabase.getInstance().getReference("routine");
        routineChild = routineDatabase.child(GlobalUser.global_username);
        //This will fill the list of workouts with the database elements
        routineChild.addValueEventListener(this);

        position_data = (String) getIntent().getStringExtra("workout_position");

        //Getting the list of exercises from the selected workout
        //Inside the list of exercises there is an ArrayList of exercises


        //Toast.makeText(this, "The global username in this activity is " + GlobalUser.global_username, Toast.LENGTH_LONG ).show();

        mapOfWorkouts = new HashMap<>();

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnProfile:
                //Redirect
                Intent profile = new Intent(getBaseContext(), Profile_Edit_Activity.class);
                startActivity(profile);
                this.finish();
                break;
            case R.id.btnHome:
                //Redirect
                Intent homePage = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
                startActivity(homePage);
                this.finish();
                break;
            case R.id.btnListRoutine:
                //Redirect
                Intent listRoutinePage = new Intent(getBaseContext(), ListRoutine_Daily_Activity.class);
                startActivity(listRoutinePage);
                this.finish();
                break;
            case R.id.btnCreateRoutine:
                //Redirect
                Intent createRoutinePage = new Intent(getBaseContext(), CreateRoutine_Day_Activity.class);
                startActivity(createRoutinePage);
                this.finish();
                break;
        }

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists())
        {
            //mapOfWorkouts = (HashMap) snapshot.child("listOfWorkouts").getValue();
            //listOfWorkouts = (ArrayList<Workout>) snapshot.child("listOfWorkouts").getValue();
            listOfWorkouts = (ArrayList<Workout>) snapshot.child("listOfWorkouts").getValue();
            //Log.d("HASH_MAP", listOfWorkouts.toString());
            // workout = (Workout) snapshot.child(position_data).getValue();
            HashMap<String, ArrayList<Exercise>> exercises = listOfWorkouts.get(Integer.parseInt(position_data));
            //listOfExercises = listOfWorkouts.get(Integer.parseInt(position_data));

            //listOfExercises = listOfWorkouts.get(Integer.valueOf(position_data));
            exerciseAdapter = new ExerciseAdapter(this, exercises.get("workout"));
            lvExercises.setAdapter(exerciseAdapter);
            exerciseAdapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(getBaseContext(), "There is no routine with this id.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        try {

            Intent exercise = new Intent(getBaseContext(), ListRoutine_Exercise_Activity.class);
            int num = i + 1;



            HashMap<String, ArrayList<Exercise>> exercises_map = listOfWorkouts.get(Integer.parseInt(position_data));
            ArrayList<Exercise> exercises_temp = exercises_map.get("workout");
            HashMap<String, String> exercise_hash = exercises_temp.get(i);

            exercise.putExtra("exerciseNumber", num);
            exercise.putExtra("exerciseName", exercise_hash.get("name"));
            exercise.putExtra("exerciseTargetMuscle",exercise_hash.get("body_part"));
            exercise.putExtra("imgExercise",exercise_hash.get("image"));


            startActivity(exercise);
            this.finish();

        }catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());
        }



    }
}