package com.example.grift.flaxgt_individual_project_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    String questionOneSelectedAnswer = "", questionTwoSelectedAnswer="", questionThreeSelectedAnswer="",
           questionFourSelectedAnswer="";
    ArrayList<String> questionFiveSelectedAnswers = new ArrayList<String>();
    String[] questionOneAnswers;
    String[] questionTwoAnswers;
    String[] questionThreeAnswers;
    String[] questionFourAnswers;
    String[] questionFiveAnswers;
    CheckBox q5a1, q5a2, q5a3, q5a4;

    RadioGroup rg1, rg2, rg3, rg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //retrieves the question answers from the string.xml file
        questionOneAnswers = getResources().getStringArray(R.array.question_1_answers);
        questionTwoAnswers = getResources().getStringArray(R.array.question_2_answers);
        questionThreeAnswers = getResources().getStringArray(R.array.question_3_answers);
        questionFourAnswers = getResources().getStringArray(R.array.question_4_answers);
        questionFiveAnswers = getResources().getStringArray(R.array.question_5_answers);

        //shuffles the answers so that they display in different locations
        Collections.shuffle(Arrays.asList(questionOneAnswers));
        Collections.shuffle(Arrays.asList(questionTwoAnswers));
        Collections.shuffle(Arrays.asList(questionThreeAnswers));
        Collections.shuffle(Arrays.asList(questionFourAnswers));
        Collections.shuffle(Arrays.asList(questionFiveAnswers));

        //associates the radio groups to access them and their children (the question answers)
        rg1 = (RadioGroup)findViewById(R.id.question_1_radio_group);
        rg2 = (RadioGroup)findViewById(R.id.question_2_radio_group);
        rg3 = (RadioGroup)findViewById(R.id.question_3_radio_group);
        rg4 = (RadioGroup)findViewById(R.id.question_4_radio_group);

        //associates the question five checkboxes
        q5a1 = (CheckBox)findViewById(R.id.question_5_answer_1_check_box);
        q5a2 = (CheckBox)findViewById(R.id.question_5_answer_2_check_box);
        q5a3 = (CheckBox)findViewById(R.id.question_5_answer_3_check_box);
        q5a4 = (CheckBox)findViewById(R.id.question_5_answer_4_check_box);

        //the question's answers are now assigned to specific radio buttons/checkboxes
        for(int i = 0; i < questionOneAnswers.length; i++)
            ((RadioButton)rg1.getChildAt(i)).setText(questionOneAnswers[i]);
        for(int i = 0; i < questionTwoAnswers.length; i++)
            ((RadioButton)rg2.getChildAt(i)).setText(questionTwoAnswers[i]);
        for(int i = 0; i < questionThreeAnswers.length; i++)
            ((RadioButton)rg3.getChildAt(i)).setText(questionThreeAnswers[i]);
        for(int i = 0; i < questionFourAnswers.length; i++)
            ((RadioButton)rg4.getChildAt(i)).setText(questionFourAnswers[i]);
        q5a1.setText(questionFiveAnswers[0]);
        q5a2.setText(questionFiveAnswers[1]);
        q5a3.setText(questionFiveAnswers[2]);
        q5a4.setText(questionFiveAnswers[3]);
    }

    public void save_question_one(View view) {
        //saves the answer to question one when a radio button is clicked
        RadioButton rb = (RadioButton)view;
        questionOneSelectedAnswer = rb.getText().toString();
        Toast.makeText(QuizActivity.this, "Question 1 answer saved.", Toast.LENGTH_SHORT).show();
    }

    public void save_question_two(View view) {
        //saves the answer to question two when a radio button is clicked
        RadioButton rb = (RadioButton)view;
        questionTwoSelectedAnswer = rb.getText().toString();
        Toast.makeText(QuizActivity.this, "Question 2 answer saved.", Toast.LENGTH_SHORT).show();
    }

    public void save_question_three(View view) {
        //saves the answer to question three when a radio button is clicked
        RadioButton rb = (RadioButton)view;
        questionThreeSelectedAnswer = rb.getText().toString();
        Toast.makeText(QuizActivity.this, questionThreeSelectedAnswer + " Question 3 answer saved.", Toast.LENGTH_SHORT).show();
    }

    public void save_question_four(View view) {
        //saves the answer to question four when a radio button is clicked
        RadioButton rb = (RadioButton)view;
        questionFourSelectedAnswer = rb.getText().toString();
        Toast.makeText(QuizActivity.this, "Question 4 answer saved.", Toast.LENGTH_SHORT).show();
    }

    public void save_question_five(View view) {
        //modifies the answer to question five when a checkbox is either checked (selecting a choice) or unchecked
        //(removing a choice)
        CheckBox cb = (CheckBox)view;
        if(cb.isChecked())
            questionFiveSelectedAnswers.add(cb.getText().toString());
        else
            questionFiveSelectedAnswers.remove(cb.getText().toString());
        Toast.makeText(QuizActivity.this, "Question 5 answer modified.", Toast.LENGTH_SHORT).show();
    }

    public void submit_quiz(View view) {
        //checks the numbers of question's that are correct by checking the answer the user clicked. then the stat
        //is recorded in the user_stats file so that it may be displayed later if the user clicks the show stats
        //button on the previous page. then, the activity sends back the quiz result to be displayed on the previous
        //screen. only occurs if every answer has been answered.
        if(!(questionOneSelectedAnswer.isEmpty() || questionTwoSelectedAnswer.isEmpty() ||
                questionThreeSelectedAnswer.isEmpty() || questionFourSelectedAnswer.isEmpty() ||
                questionFiveSelectedAnswers.isEmpty()))
        {
            int numberOfCorrectAnswers = 0;
            if (Integer.parseInt(questionOneSelectedAnswer) == (2 + 2))
                numberOfCorrectAnswers++;
            if (Integer.parseInt(questionTwoSelectedAnswer) == (7 + 5))
                numberOfCorrectAnswers++;
            if (Integer.parseInt(questionThreeSelectedAnswer) == (3 * 7))
                numberOfCorrectAnswers++;
            if (Integer.parseInt(questionFourSelectedAnswer) == (100 * 15))
                numberOfCorrectAnswers++;
            ArrayList<String> correctAnswersQ5 = new ArrayList<String>();
            for (String i : questionFiveAnswers)
                if (Integer.parseInt(i) % 2 == 0)
                    correctAnswersQ5.add(i);
            if (correctAnswersQ5.equals(questionFiveSelectedAnswers))
                numberOfCorrectAnswers++;
            SharedPreferences user_data_preferences = getSharedPreferences("userdata", MODE_PRIVATE);
            String username = user_data_preferences.getString("username", null);
            SharedPreferences user_stats_preferences = getSharedPreferences(username + "_stats", MODE_PRIVATE);
            Set<String> user_stats = user_stats_preferences.getStringSet("user_stats", new HashSet<String>());
            user_stats.add(Integer.toString(numberOfCorrectAnswers));
            user_stats_preferences.edit().putStringSet("user_stats", user_stats).clear().commit();
            Intent intent = new Intent(QuizActivity.this, QuizStartActivity.class);
            intent.putExtra("quiz_result", Integer.toString(numberOfCorrectAnswers));
            startActivity(intent);
        }
    }
}
