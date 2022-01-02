package com.example.scrumfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scrumfitapp.model.GlobalUser;

public class Home_Dashboard_Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnHome, btnListRoutine, btnCreateRoutine, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);
        initialize();
    }

    private void initialize()
    {
        //Initialization of views
        btnHome = findViewById(R.id.btnHome);
        btnListRoutine = findViewById(R.id.btnListRoutine);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome.setOnClickListener(this);
        btnListRoutine.setOnClickListener(this);
        btnCreateRoutine.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        Toast.makeText(this, "The global username in this activity is " + GlobalUser.global_username, Toast.LENGTH_LONG ).show();

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
//                Intent homePage = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
//                startActivity(homePage);
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
}