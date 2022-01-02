package com.example.scrumfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class ListRoutine_Exercise_Activity extends AppCompatActivity implements View.OnClickListener{

    //Declaration of views
    TextView tvTitle, tvExerciseName, tvTargetMuscle;
    ImageView imExercise;
    Button btnHome, btnListRoutine, btnCreateRoutine, btnProfile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_routine_exercise);
        initialize();
    }

    private void initialize()
    {

        tvTitle = findViewById(R.id.tvNoExercise);
        tvExerciseName =findViewById(R.id.edExerciseName);
        tvTargetMuscle = findViewById(R.id.edTargetMuscle);
        imExercise = findViewById(R.id.imgExercise);


        btnHome = findViewById(R.id.btnHome);
        btnListRoutine = findViewById(R.id.btnListRoutine);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnProfile = findViewById(R.id.btnProfile);

        btnHome.setOnClickListener(this);
        btnListRoutine.setOnClickListener(this);
        btnCreateRoutine.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        int exerciseNumber = getIntent().getIntExtra("exerciseNumber", 0);
        String name = getIntent().getStringExtra("exerciseName");
        String targetMuscle = getIntent().getStringExtra("exerciseTargetMuscle");
        String str = "Exercise #" + exerciseNumber;

        tvTitle.setText(str);
        tvExerciseName.setText(name);
        tvTargetMuscle.setText(targetMuscle);

        String animatedGifName = getIntent().getStringExtra("imgExercise");
        Picasso
                .with(this)
                .load(animatedGifName)
                .placeholder(R.drawable.exercise)
                .into(imExercise);

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
}