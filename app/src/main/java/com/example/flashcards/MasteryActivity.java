package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;

public class MasteryActivity extends AppCompatActivity {

    public DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastery);

        //Testing for mastery.status from db
        dbHelper = new DataBaseHelper(this);
        int value = dbHelper.getMastery();
        Log.d("Flash Card Value", "Mastery Value "+value);
        Toast.makeText(MasteryActivity.this,"Mastery value = "+value,Toast.LENGTH_SHORT).show();
        updateProgress();
    }

    public void launchHome(View v){
        // Returns to the home screen via a button

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void updateProgress(){
        // updates the progress graphics to reflect the entered progress. This will be moved to the
        // Oncreate function and only need to run once when we are able to have the mastery passed
        // into this activity

//        // Get reference of the progress value (for now just entered by user)
//        TextView progress = findViewById(R.id.progress_text);
//        // Convert input to string, and then to an int
//        String input = progress.getText().toString();
//        int progressnumber = Integer.parseInt(input);
        int progressnumber = dbHelper.getMastery();

        // Create the offset using the progress (out of 100)
        int astronautoffset = progressnumber * -12;

        // Get the image of the astronaut and set it's transformation in the y direction to the offset
        ImageView astronaut = findViewById(R.id.astronaut_image);
        astronaut.setTranslationY(astronautoffset);
    }

}