package com.example.scrumfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scrumfitapp.model.GlobalUser;

public class CreateRoutine_Day_Activity extends AppCompatActivity implements View.OnClickListener {

    //Declaration of Views
    Button btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6;
    Button btnHome, btnListRoutine, btnCreateRoutine, btnProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine_day);
        initialize();

    }

    private void initialize() {
        //Initialization of views
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        btnNumber6 = findViewById(R.id.btnNumber6);
        btnHome = findViewById(R.id.btnHome);
        btnListRoutine = findViewById(R.id.btnListRoutine);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnProfile = findViewById(R.id.btnProfile);

        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnListRoutine.setOnClickListener(this);
        btnCreateRoutine.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        Toast.makeText(this, "The global username in this activity is " + GlobalUser.global_username, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
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
//                Intent createRoutinePage = new Intent(getBaseContext(), CreateRoutine_Day_Activity.class);
//                startActivity(createRoutinePage);
                break;
            case R.id.btnNumber2:
                Intent selectExercise2 = new Intent(getBaseContext(), CreateRoutine_SelectExercise_Activity.class);
                GlobalUser.number_of_days = 2;
                startActivity(selectExercise2);
                this.finish();
                break;
            case R.id.btnNumber3:
                Intent selectExercise3 = new Intent(getBaseContext(), CreateRoutine_SelectExercise_Activity.class);
                GlobalUser.number_of_days = 3;
                startActivity(selectExercise3);
                this.finish();
                break;
            case R.id.btnNumber4:
                Intent selectExercise4 = new Intent(getBaseContext(), CreateRoutine_SelectExercise_Activity.class);
                GlobalUser.number_of_days = 4;
                startActivity(selectExercise4);
                this.finish();
                break;
            case R.id.btnNumber5:
                Intent selectExercise5 = new Intent(getBaseContext(), CreateRoutine_SelectExercise_Activity.class);
                GlobalUser.number_of_days = 5;
                startActivity(selectExercise5);
                this.finish();
                break;
            case R.id.btnNumber6:
                Intent selectExercise6 = new Intent(getBaseContext(), CreateRoutine_SelectExercise_Activity.class);
                GlobalUser.number_of_days = 6;
                startActivity(selectExercise6);
                this.finish();
                break;

        }
    }
}