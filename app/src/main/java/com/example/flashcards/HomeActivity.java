package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void launchMastery(View v){
        // Adding sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
        mediaPlayer.start();

        // Switches to the mastery screen via a button
        Intent i = new Intent(this, MasteryActivity.class);
        startActivity(i);
    }

    public void launchPractice(View v){
        // Adding sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
        mediaPlayer.start();
        // Switches to the practice screen via a button AFTER passing through topic select
        Intent i = new Intent(this, TopicSelectActivity.class);
        i.putExtra("mode","practice");
        startActivity(i);
    }

    public void launchQuizzes(View v){
        // Adding sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
        mediaPlayer.start();
        // Switches to the quiz screen via a button AFTER passing through topic select
        Intent i = new Intent(this, TopicSelectActivity.class);
        i.putExtra("mode", "Quiz");
        startActivity(i);
    }
}