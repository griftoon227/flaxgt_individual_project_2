package com.example.grift.flaxgt_individual_project_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstNameETV, familyNameETV, dateOfBirthETV, emailETV, passwordETV, confirmPasswordETV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //associate all of the edit text fields so that data can be retrieved from them
        firstNameETV = (EditText)findViewById(R.id.first_name_text_view);
        familyNameETV = (EditText)findViewById(R.id.family_name_text_view);
        dateOfBirthETV = (EditText)findViewById(R.id.date_of_birth_text_view);
        emailETV = (EditText)findViewById(R.id.email_text_view);
        passwordETV = (EditText)findViewById(R.id.password_text_view);
        confirmPasswordETV = (EditText)findViewById(R.id.confirm_password_text_view);
    }

    //event handler that validates and confirms all registration information that is sent back to the start page and
    //enables login
    public void confirm_information(View view) {
        //check if the fields are empty first, show a Toast in case any of them are
        if(firstNameETV.getText().toString().isEmpty() ||
                familyNameETV.getText().toString().isEmpty() ||
                dateOfBirthETV.getText().toString().isEmpty() ||
                emailETV.getText().toString().isEmpty() ||
                passwordETV.getText().toString().isEmpty() ||
                confirmPasswordETV.getText().toString().isEmpty())
        {
            Toast.makeText(RegistrationActivity.this, "Please make sure all required fields are filled.",
                    Toast.LENGTH_LONG).show();
        }
        //validate the fields and show a Toast if any field does not contain valid information
        else if(!(emailETV.getText().toString().endsWith("@example.com")) ||
                !(firstNameETV.getText().toString().length() >= 3 && firstNameETV.getText().toString().length() <= 30) ||
                !(familyNameETV.getText().toString().length() >= 3 && familyNameETV.getText().toString().length() <= 30) ||
                !(passwordETV.getText().toString().equals(confirmPasswordETV.getText().toString())))
        {
            Toast.makeText(RegistrationActivity.this, "Please ensure all fields were entered properly.", Toast.LENGTH_LONG).show();
        }
        //finally, if all is good and valid, the username and password are stored in a file, and then a file is
        //generated using the username entered by the user that stores all of the quix stats, and then the activity
        //is moved back to the start page
        else {
            SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("username", emailETV.getText().toString());
            edit.putString("password", passwordETV.getText().toString());
            edit.commit();
            SharedPreferences user_stats_preferences = getSharedPreferences(emailETV.getText().toString()+"_stats",
                    MODE_PRIVATE);
            user_stats_preferences.edit().putStringSet("user_stats", new HashSet<String>()).commit();
            startActivity(new Intent(RegistrationActivity.this, StartScreenActivity.class));
        }
    }
}
