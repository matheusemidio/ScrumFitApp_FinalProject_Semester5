package com.example.scrumfitapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.scrumfitapp.model.Exercise;
import com.example.scrumfitapp.model.Exercise_Model;
import com.example.scrumfitapp.model.SelectorExerciseAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateRoutine_ListOfExercises_Activity extends AppCompatActivity implements  AdapterView.OnItemClickListener,ChildEventListener {

    DatabaseReference exercisesDatabase, exerciseChild;
    ArrayList<Exercise_Model> listOfExercises;
    Exercise_Model exercise;
    ListView lvExercises;
    SelectorExerciseAdapter listOfExercisesAdapter;
    FirebaseStorage storage;
    StorageReference storageReference;
    //Exercise exerciseData;
    String position_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine_list_of_exercises);
        initialize();

    }

    private void initialize()
    {
        try
        {
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            exercisesDatabase = FirebaseDatabase.getInstance().getReference("exercises");
            exercisesDatabase.addChildEventListener(this);

            lvExercises = findViewById(R.id.lvSelectExercises);

            listOfExercises = new ArrayList<Exercise_Model>();
            exercise = new Exercise_Model();

            listOfExercisesAdapter = new SelectorExerciseAdapter(this, listOfExercises);
            lvExercises.setAdapter(listOfExercisesAdapter);
            lvExercises.setOnItemClickListener(this);

            //Getting the serializable exercise
            //exerciseData = (Exercise) getIntent().getExtras().getSerializable("exercise");
            position_data = (String) getIntent().getStringExtra("exercise_position");
        }
        catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());
        }




    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        String body_part = snapshot.child("body_part").getValue().toString();
        String equipment = snapshot.child("equipment").getValue().toString();
        String name = snapshot.child("name").getValue().toString();
        String image = snapshot.child("image").getValue().toString();


        //exercise = new Exercise(body_part,equipment,image,name);

        //Exercise exercise = snapshot.getValue(Exercise.class);
        //HashMap<String, String> exercise_mao = snapshot.getValue(Exercise.class);
        Exercise_Model exercise = new Exercise_Model(body_part,equipment,image,name);
        Log.d("EXERCISE", exercise.toString());
        listOfExercises.add(exercise);
        listOfExercisesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent intent = new Intent();

        try
        {
            //Getting the exercise that was clicked
            //Exercise new_exercise = listOfExercises.get(i);
            Log.d("HASH_MAP", listOfExercises.get(i).getBody_part() + " " + listOfExercises.get(i).getEquipment() + " " + listOfExercises.get(i).getName());
            //HashMap<String, String> exercise_map = listOfExercises.get(i);
//            String body_part = exercise_map.get("body_part");
//            String equipment = exercise_map.get("equipment");
//            String name = exercise_map.get("name");
//            String image = exercise_map.get("image");
            String body_part = listOfExercises.get(i).getBody_part();
            String equipment = listOfExercises.get(i).getEquipment();
            String name = listOfExercises.get(i).getName();
            String image = listOfExercises.get(i).getImage();
            //Exercise_Model new_exercise = new Exercise_Model(body_part,equipment,image, name);
            Exercise_Model new_exercise = listOfExercises.get(i);

            //Sending the data back to the previous view
            if(new_exercise != null)
            {
                intent.putExtra("new_exercise", new_exercise);
                setResult(RESULT_OK, intent);


            }
            else
            {
                setResult(RESULT_CANCELED, intent);
            }
            //intent.putExtra("exercise_position", position_data);


            //Intent intent = new Intent(this, CreateRoutine_ListOfExercises_Activity.class);
            //intent.putExtra("exercise", exercise);
            //activityResultLauncher.launch(intent);
        }
        catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());
            setResult(RESULT_CANCELED, intent);
        }
        this.finish();
    }
}