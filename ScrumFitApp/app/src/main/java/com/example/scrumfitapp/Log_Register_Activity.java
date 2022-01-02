package com.example.scrumfitapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Log_Register_Activity extends AppCompatActivity implements View.OnClickListener{

    //Declaration of Views
    EditText edUsername, edName, edPassword, edEmail, edHeight, edWeight, edBirthday;
    Button btnRegister;
    TextView btnSignIn;
    DatabaseReference userDatabase, userChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_register);
        initialize();
    }

    private void initialize()
    {
        //Initialization of views
        edUsername = findViewById(R.id.edUsername);
        edName = findViewById(R.id.edName);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        edHeight = findViewById(R.id.edHeight);
        edWeight = findViewById(R.id.edWeight);
        edBirthday = findViewById(R.id.edBirthday);
        btnRegister = findViewById(R.id.btnRegister);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        //Database reference
        userDatabase = FirebaseDatabase.getInstance().getReference("user");


    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnSignIn:
                signInRedirect();
                break;
            case R.id.btnRegister:
                signUp();
                break;

        }

    }

    private void signUp()
    {
        try
        {
            //Still needs to be validated
            String username = edUsername.getText().toString();
            String name = edName.getText().toString();
            String password = edPassword.getText().toString();
            String email = edEmail.getText().toString();
            String height = edHeight.getText().toString();
            String weight = edWeight.getText().toString();
            String birthday = edBirthday.getText().toString();

            User user = new User(username,birthday,email,height,name,password,weight);

            //Still needs to check if there is no user with that username on the database
            userChild = userDatabase.child(username);
            userChild.setValue(user);
            clearWidgets();
            Toast.makeText(this, "The user " + username +" was successfully registered.", Toast.LENGTH_LONG).show();

            //Set the global username
            GlobalUser.global_username = username;

            //Sign In for the user
            Intent intent = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
            startActivity(intent);
            this.finish();

        }
        catch (Exception e)
        {
            Log.d("REGISTRATION", e.getMessage());
            Toast.makeText(this, "Some error occurred, please try again.", Toast.LENGTH_LONG).show();
        }
    }

    private void clearWidgets()
    {
        edUsername.setText(null);
        edName.setText(null);
        edPassword.setText(null);
        edEmail.setText(null);
        edHeight.setText(null);
        edWeight.setText(null);
        edBirthday.setText(null);
    }

    private void signInRedirect()
    {
        Intent intent = new Intent(getBaseContext(), Log_SignIn_Acitity.class);
        startActivity(intent);
        this.finish();
    }
}