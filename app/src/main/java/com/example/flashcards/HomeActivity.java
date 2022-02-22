package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void launchMastery(View v){
        // Switches to the mastery screen via a button

        Intent i = new Intent(this, MasteryActivity.class);
        startActivity(i);
    }

    public void launchPractice(View v){
        // Switches to the practice screen via a button

        Intent i = new Intent(this, PracticeActivity.class);
        startActivity(i);
    }

    public void launchQuizzes(View v){
        // Switches to the quiz screen via a button

        Intent i = new Intent(this, QuizzesActivity.class);
        startActivity(i);
    }
}