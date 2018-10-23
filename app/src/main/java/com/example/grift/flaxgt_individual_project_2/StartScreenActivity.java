package com.example.grift.flaxgt_individual_project_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grift.flaxgt_individual_project_2.LoginActivity;
import com.example.grift.flaxgt_individual_project_2.RegistrationActivity;

import java.io.File;

public class StartScreenActivity extends AppCompatActivity {

    Button regBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //associate the buttons for usage
        regBtn = findViewById(R.id.registration_btn);
        loginBtn = findViewById(R.id.login_btn);

        //ensure that the button is not enabled when the user is not registered yet
        File f = new File(getResources().getString(R.string.shared_preferences_file_path));
        if(!f.exists())
            loginBtn.setEnabled(false);
    }

    //move to a new screen depending on what button is clicked
    public void move_to_next_screen(View view) {
        //the view is a button
        Button btn = (Button)view;

        //go to the registration screen if its the registration button
        if (btn.getId() == regBtn.getId())
            startActivity(new Intent(StartScreenActivity.this, RegistrationActivity.class));
        //go to the login screen if its the login button
        else if(btn.getId() == loginBtn.getId()) {
            startActivity(new Intent(StartScreenActivity.this, LoginActivity.class));
        }
    }
}
