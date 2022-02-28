package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;
import com.example.flashcards.models.FlashCardModel;

public class MasteryActivity extends AppCompatActivity {

    public DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastery);

        //Testing for mastery.status from db
        dbHelper = new DataBaseHelper(this);
        String value = dbHelper.getMastery();
        Log.d("Flash Card Value", "Mastery Value "+value);
        Toast.makeText(MasteryActivity.this,value,Toast.LENGTH_SHORT).show();
    }

    public void launchHome(View v){
        // Returns to the home screen via a button

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}