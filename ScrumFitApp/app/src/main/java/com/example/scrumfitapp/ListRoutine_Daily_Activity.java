package com.example.scrumfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scrumfitapp.model.GlobalUser;
import com.example.scrumfitapp.model.SelectorWorkoutAdapter;
import com.example.scrumfitapp.model.Workout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ListRoutine_Daily_Activity extends AppCompatActivity implements View.OnClickListener, ValueEventListener, AdapterView.OnItemClickListener {

    //Declaration of views
    ListView lvDays;
    Button btnHome, btnListRoutine, btnCreateRoutine, btnProfile;
    SelectorWorkoutAdapter selectorWorkoutAdapter;
    ArrayList<Workout> listOfWorkouts;
    DatabaseReference routineDatabase, routineChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_routine_daily);

        initialize();
    }

    private void initialize()
    {
        try
        {

            //GlobalUser.global_username = "matheus";
            //Initialization of views
            btnHome = findViewById(R.id.btnHome);
            btnListRoutine = findViewById(R.id.btnListRoutine);
            btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
            btnProfile = findViewById(R.id.btnProfile);
            lvDays = findViewById(R.id.lvDays);

            btnHome.setOnClickListener(this);
            btnListRoutine.setOnClickListener(this);
            btnCreateRoutine.setOnClickListener(this);
            btnProfile.setOnClickListener(this);

            listOfWorkouts = new ArrayList<Workout>();

            routineDatabase = FirebaseDatabase.getInstance().getReference("routine");
            routineChild = routineDatabase.child(GlobalUser.global_username);
            //This will fill the list of workouts with the database elements
            routineChild.addValueEventListener(this);

            lvDays.setOnItemClickListener(this);


            //Toast.makeText(this, "The global username in this activity is " + GlobalUser.global_username, Toast.LENGTH_LONG ).show();
        }
        catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());

        }


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
//                Intent listRoutinePage = new Intent(getBaseContext(), ListRoutine_Workout_Activity.class);
//                startActivity(listRoutinePage);
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
    public void onDataChange(@NonNull DataSnapshot snapshot)
    {
        if(snapshot.exists())
        {
            //HashMap mapWorkouts = (HashMap) snapshot.child("listOfWorkouts").getValue();
            listOfWorkouts = (ArrayList<Workout>) snapshot.child("listOfWorkouts").getValue();
            Log.d("HASH_MAP", listOfWorkouts.toString());

            //listOfWorkouts.add( (ArrayList<Workout>) snapshot.child("listOfWorkouts").getValue());
            //Workout workout = new Workout();
            //Log.d("HASH_MAP", mapWorkouts.toString());
            //workout.setId(mapWorkouts);
            //listOfWorkouts.add(mapWorkouts.values());
            selectorWorkoutAdapter = new SelectorWorkoutAdapter(this, listOfWorkouts);
            lvDays.setAdapter(selectorWorkoutAdapter);

            selectorWorkoutAdapter.notifyDataSetChanged();


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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        try
        {
            Intent intent = new Intent(this, ListRoutine_Workout_Activity.class);
            intent.putExtra("workout_position", String.valueOf(position));
            startActivity(intent);
            this.finish();
            //list_position = position;
            //activityResultLauncher.launch(intent);
        }
        catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());

        }

    }
}