package com.example.scrumfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrumfitapp.model.GlobalUser;
import com.example.scrumfitapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Edit_Activity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    //Declaration of Views
    EditText edName, edPassword, edEmail, edHeight, edWeight, edBirthday;
    Button btnSave, btnHome, btnListRoutine, btnCreateRoutine, btnProfile;;
    DatabaseReference userDatabase, userChild;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        initialize();

    }
    private void initialize()
    {
        //Initialization of views
        username = GlobalUser.global_username;
        edName = findViewById(R.id.edName);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        edHeight = findViewById(R.id.edHeight);
        edWeight = findViewById(R.id.edWeight);
        edBirthday = findViewById(R.id.edBirthday);
        btnSave = findViewById(R.id.btnSave);
        btnHome = findViewById(R.id.btnHome);
        btnListRoutine = findViewById(R.id.btnListRoutine);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome.setOnClickListener(this);
        btnListRoutine.setOnClickListener(this);
        btnCreateRoutine.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        //Database reference
        userDatabase = FirebaseDatabase.getInstance().getReference("user");

        Toast.makeText(this, "The global username in this activity is " + GlobalUser.global_username, Toast.LENGTH_LONG ).show();
        //Load the logged user and set the text fields with his information
        loadUser();

    }



    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnProfile:
                break;
            case R.id.btnHome:
                //Redirect
                Intent homePage = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
                startActivity(homePage);
                this.finish();
                break;
            case R.id.btnListRoutine:
                //Redirect
                Intent listRoutinePage = new Intent(getBaseContext(), ListRoutine_Workout_Activity.class);
                startActivity(listRoutinePage);
                this.finish();
                break;
            case R.id.btnCreateRoutine:
                //Redirect
                Intent createRoutinePage = new Intent(getBaseContext(), CreateRoutine_Day_Activity.class);
                startActivity(createRoutinePage);
                this.finish();
                break;
            case R.id.btnSave:
                updateProfile();
                break;
        }
    }

    private void updateProfile()
    {
        try
        {
            //Still needs to be validated
            String name = edName.getText().toString();
            String password = edPassword.getText().toString();
            String email = edEmail.getText().toString();
            String height = edHeight.getText().toString();
            String weight = edWeight.getText().toString();
            String birthday = edBirthday.getText().toString();

            User user = new User(username,birthday,email,height,name,password,weight);
            userChild = userDatabase.child(username);
            userChild.setValue(user);
            clearWidgets();
            Toast.makeText(this, "The user " + username +" was successfully registered.", Toast.LENGTH_LONG).show();


        }
        catch (Exception e)
        {
            Log.d("PROFILE_UPDATE", e.getMessage());
            Toast.makeText(this, "Some error occurred, please try again.", Toast.LENGTH_LONG).show();
        }
    }

    private void clearWidgets()
    {
        edName.setText(null);
        edPassword.setText(null);
        edEmail.setText(null);
        edHeight.setText(null);
        edWeight.setText(null);
        edBirthday.setText(null);
    }
    private void loadUser()
    {
        try
        {
            userChild = userDatabase.child(username);
            userChild.addValueEventListener(this);
        }
        catch (Exception e)
        {
            Log.d("PROFILE_LOAD_USER", e.getMessage());
            Toast.makeText(this, "Some error occurred, please try again.", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot)
    {
        //Still needs to be validated
        String name = snapshot.child("name").getValue().toString();
        String password = snapshot.child("password").getValue().toString();
        String email = snapshot.child("email").getValue().toString();
        String height = snapshot.child("height").getValue().toString();
        String weight = snapshot.child("weight").getValue().toString();
        String birthday = snapshot.child("birthday").getValue().toString();

        //User exists
        try
        {
            if (snapshot.exists())
            {
                edName.setText(name);
                edPassword.setText(password);
                edEmail.setText(email);
                edHeight.setText(height);
                edWeight.setText(weight);
                edBirthday.setText(birthday);
            }
            else
            {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error)
    {

    }
}