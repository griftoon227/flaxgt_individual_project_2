package com.example.grift.flaxgt_individual_project_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grift.flaxgt_individual_project_2.StartScreenActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameETV, passwordETV;
    String username = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //associate the edit text views for data retrieval
        usernameETV = (EditText)findViewById(R.id.username_etv);
        passwordETV = (EditText)findViewById(R.id.password_etv);
    }

    public void confirm_login(View view) {
        //ensure the password and username are not empty, and if they are, show a Toast
        if(usernameETV.getText().toString().isEmpty())
            Toast.makeText(LoginActivity.this, "Please enter a valid username.", Toast.LENGTH_LONG).show();
        else if(passwordETV.getText().toString().isEmpty())
            Toast.makeText(LoginActivity.this, "Please enter a valid password.", Toast.LENGTH_LONG).show();
        else {
            //need to confirm username & password existence & then login & return to previous screen, otherwise
            //show a toast saying the entered username and password are not correct and need to be re-entered
            SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
            username = sharedPreferences.getString("username", "");
            password = sharedPreferences.getString("password", "");
            if (usernameETV.getText().toString().equals(username) && passwordETV.getText().toString().equals(password)) {
                startActivity(new Intent(LoginActivity.this, QuizStartActivity.class));
            }
            else
                Toast.makeText(LoginActivity.this, "The username or password you entered is incorrect.",
                        Toast.LENGTH_LONG).show();
        }
    }
}
