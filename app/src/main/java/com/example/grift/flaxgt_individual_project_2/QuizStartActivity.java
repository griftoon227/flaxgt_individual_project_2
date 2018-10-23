package com.example.grift.flaxgt_individual_project_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class QuizStartActivity extends AppCompatActivity {
    private int quiz_result;
    private TextView tv, show_stats_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        //associates the text views
        tv = (TextView)findViewById(R.id.show_text);
        show_stats_tv = (TextView)findViewById(R.id.show_stats_text);

        //gets the quiz result if there is one sent
        Intent intent = getIntent();
        Bundle extrasBundle = intent.getExtras();

        //checks if there is data in the bundle and if it exists
        if(extrasBundle != null && !extrasBundle.isEmpty())
        {
            //quiz result is stored and then displayed
            quiz_result = Integer.parseInt(extrasBundle.getString("quiz_result"));
            show_stats_tv.setText("Quiz Result\n" + quiz_result + " / 5");
        }
    }

    public void start_quiz(View view) {
        //starts the quiz activity
        startActivity(new Intent(QuizStartActivity.this, QuizActivity.class));
    }

    public void show_rules(View view) {
        //displays the rules text to the user
        show_stats_tv.setText("");
        tv.setText(getString(R.string.rules_text));
    }

    public void logout(View view) {
        //logs the user out by returning to the login page
        startActivity(new Intent(QuizStartActivity.this, LoginActivity.class));
    }

    public void show_stats(View view) {
        //displays the all time quiz results by using the username-defined stats file and displaying a String object
        //that encapsulates all of the results within the set data structure defined within the user stats file
        show_stats_tv.setText("");
        SharedPreferences user_data_preferences = getSharedPreferences("userdata", MODE_PRIVATE);
        String username = user_data_preferences.getString("username", null);
        SharedPreferences user_stats_preferences = getSharedPreferences(username+"_stats", MODE_PRIVATE);
        Set<String> user_stats = user_stats_preferences.getStringSet("user_stats", null);
        String text = "Quiz Results (All Attempts)\n";
        for(String i : user_stats)
            text+=i + " / 5\n";
        tv.setText(text);
    }
}
