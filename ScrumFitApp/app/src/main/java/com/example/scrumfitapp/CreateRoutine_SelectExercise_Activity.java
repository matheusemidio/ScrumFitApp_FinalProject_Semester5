package com.example.scrumfitapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrumfitapp.model.Exercise;
import com.example.scrumfitapp.model.Exercise_Model;
import com.example.scrumfitapp.model.GlobalUser;
import com.example.scrumfitapp.model.Routine;
import com.example.scrumfitapp.model.Routine_Model;
import com.example.scrumfitapp.model.SelectorExerciseAdapter;
import com.example.scrumfitapp.model.Workout;
import com.example.scrumfitapp.model.Workout_Model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateRoutine_SelectExercise_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView lvExercises;
    TextView tvDescription;
    SelectorExerciseAdapter listOfExercisesAdapter;
    ArrayList<Exercise_Model> listOfExercises;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Button btnDone;
    int list_position = -1;
    int nbDays = 1;
    Workout_Model workout;
    ArrayList<Workout_Model> listOfWorkouts;
    //ArrayList<ArrayList<Exercise>> listOfListsExercise;

    DatabaseReference routineDatabase, routineChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine_select_exercise);
        initialize();

    }

    private void initialize()
    {
        //Search if the user already have a routine and replace it
        try
        {
            tvDescription = findViewById(R.id.tvDescription);
            tvDescription.setText("Day " + nbDays);
            btnDone = findViewById(R.id.btnDone);
            lvExercises = findViewById(R.id.lvSelectExercises);

            listOfExercises = new ArrayList<Exercise_Model>();
            listOfExercises.add(new Exercise_Model("",""));
            listOfExercises.add(new Exercise_Model("",""));
            listOfExercises.add(new Exercise_Model("",""));
            listOfExercises.add(new Exercise_Model("",""));
            listOfExercises.add(new Exercise_Model("",""));
            listOfExercises.add(new Exercise_Model("",""));

            listOfExercisesAdapter = new SelectorExerciseAdapter(this,listOfExercises);
            lvExercises.setAdapter(listOfExercisesAdapter);

            lvExercises.setOnItemClickListener(this);
            btnDone.setOnClickListener(this);

            workout = new Workout_Model();
            listOfWorkouts = new ArrayList<Workout_Model>();
            routineDatabase = FirebaseDatabase.getInstance().getReference("routine");

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("ERROR", e.getMessage());
        }

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try
                        {
                            if(result.getResultCode() == RESULT_OK && result.getData() != null)
                            {
                                Log.d("POSITION",String.valueOf(list_position) );
                                //Getting the new object and the position of the element to be set
                                //Not able to get the serializable
                                //HashMap<String, String> exercise_map = (HashMap<String, String>) result.getData().getSerializableExtra("new_exercise");
                                //Log.d("ERROR", exercise_map.get("body_part") + " " + exercise_map.get("equipment") + " " + exercise_map.get("name"));
                                Exercise_Model new_exercise = (Exercise_Model) result.getData().getSerializableExtra("new_exercise");
                                //String position = getIntent().getStringExtra("exercise_position");
                                listOfExercises.set(Integer.parseInt(String.valueOf(list_position)), new_exercise);
                                listOfExercisesAdapter.notifyDataSetChanged();
                            }
                            else
                            {
                                if(result.getResultCode() == RESULT_CANCELED)
                                {
                                    Toast.makeText(getBaseContext(),"Some error occurred", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            Log.d("ERROR", e.getMessage());

                        }

                    }
                }
        );
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        try
        {
            //Getting the exercise that was clicked
            //Exercise exercise = listOfExercises.get(position);
            Intent intent = new Intent(this, CreateRoutine_ListOfExercises_Activity.class);
            intent.putExtra("exercise_position", String.valueOf(position));
            list_position = position;
            activityResultLauncher.launch(intent);
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
        Boolean done = true;
        if(id == R.id.btnDone)
        {
            for (Exercise_Model item: listOfExercises)
            {
                //Check if all the exercises were set
                if(item.getName().equals(""))
                {
                    //Still has some exercises to be set
                    Toast.makeText(getBaseContext(), "There are still some exercises to be set", Toast.LENGTH_LONG).show();
                    done = false;
                }
            }
            //All the exercises were set
            if(done)
            {
                ArrayList<Exercise_Model> temp = new ArrayList<Exercise_Model>();
                temp.add(listOfExercises.get(0));
                temp.add(listOfExercises.get(1));
                temp.add(listOfExercises.get(2));
                temp.add(listOfExercises.get(3));
                temp.add(listOfExercises.get(4));
                temp.add(listOfExercises.get(5));

                workout = new Workout_Model(String.valueOf(nbDays-1),temp);

                listOfWorkouts.add(workout);
                //All the days were filled
                if(GlobalUser.number_of_days == nbDays)
                {
                    //Create a new routine
                    Routine_Model routine = new Routine_Model(GlobalUser.global_username,
                            String.valueOf(GlobalUser.number_of_days), listOfWorkouts);

                    //Save on the database
                    routineChild = routineDatabase.child(GlobalUser.global_username);
                    routineChild.setValue(routine);
                    Toast.makeText(getBaseContext(), "The routine was successfully saved on the database", Toast.LENGTH_LONG).show();

                    //Redirect the user to home
                    Intent home = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
                    startActivity(home);
                    this.finish();
                }
                else
                {
                    //Resetting the list
                    nbDays++;
                    listOfExercises.set(0,new Exercise_Model("",""));
                    listOfExercises.set(1,new Exercise_Model("",""));
                    listOfExercises.set(2,new Exercise_Model("",""));
                    listOfExercises.set(3,new Exercise_Model("",""));
                    listOfExercises.set(4,new Exercise_Model("",""));
                    listOfExercises.set(5,new Exercise_Model("",""));
                    tvDescription.setText("Day " + nbDays);
                    //
                    //listOfExercisesAdapter.notifyDataSetChanged();
                }

            }

        }

    }
}