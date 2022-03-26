package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.res.Resources;
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
        Log.d("Flash Card Value", "Mastery Value " + value);
        Toast.makeText(MasteryActivity.this, "Mastery value = " + value, Toast.LENGTH_SHORT).show();
        updateProgress();
    }

    public void launchHome(View v) {
        // Returns to the home screen via a button

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void updateProgress() {
        // updates the progress graphics to reflect the entered progress. This will be moved to the
        // Oncreate function and only need to run once when we are able to have the mastery passed
        // into this activity

        // Get progress value and update TextView
        int progressPercent = dbHelper.getMastery();
        TextView completionPercent = findViewById(R.id.completionPercent);
        completionPercent.setText(Integer.toString(progressPercent) + "%");

        // Clone the current constraints to update them
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout constraintLayout = findViewById(R.id.moving_ship);
        set.clone(constraintLayout);
        // Update bias using progressPercent
        float progress = (float) (progressPercent / 100.0);
        set.setVerticalBias(R.id.astronaut_image, 1 - (progress * 0.95f));
        set.setHorizontalBias(R.id.astronaut_image, progress);
        set.applyTo(constraintLayout);
    }
}