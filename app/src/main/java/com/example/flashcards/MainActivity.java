package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //I presume these will be helpful for you guys:
    //https://stackoverflow.com/questions/26943038/how-to-make-clickable-and-editable-textfield-in-android
    //https://stackoverflow.com/questions/6274021/implement-uitextfield-properties-in-android
}