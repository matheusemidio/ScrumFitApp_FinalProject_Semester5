package com.example.scrumfitapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Log_SignIn_Acitity extends AppCompatActivity implements View.OnClickListener, ValueEventListener{

    //Declaration of Views
    EditText edUsername, edPassword;
    Button btnSignIn;
    TextView btnSignUp;
    DatabaseReference userDatabase, userChild;
    String DB_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign_in);
        initialize();
    }

    private void initialize()
    {
        //Initialization of views
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

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
                //Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();
                signIn();
                break;
            case R.id.btnSignUp:
                signUpRedirect();
                break;
        }

    }

    private void signUpRedirect()
    {
        Intent intent = new Intent(getBaseContext(), Log_Register_Activity.class);
        startActivity(intent);
    }

    private void signIn()
    {
        try
        {
            //Validate username is still missing
            String username = edUsername.getText().toString();
            userChild = userDatabase.child(username);
            userChild.addValueEventListener(this);
        }
        catch (Exception e)
        {
            Log.d("SIGN_IN", e.getMessage());
            Toast.makeText(this, "Some error occurred, please try again.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot)
    {
        //Validation of the password still needs to be done
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();

        //User exists
        try
        {
            if (snapshot.exists())
            {

                DB_password = snapshot.child("password").getValue().toString();
                if(DB_password.equals(password))
                {
                    //Set the global username
                    GlobalUser.global_username = username;

                    //Passed the verification -> allow the user to access the app
                    Intent intent = new Intent(getBaseContext(), Home_Dashboard_Activity.class);
                    startActivity(intent);
                    this.finish();
                }
                //Authentication was not successful
                else
                {
                    Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
                }
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
    public void onCancelled(@NonNull DatabaseError error) {

    }
}