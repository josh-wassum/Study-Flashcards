package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // View controller code below!
    // TODO: Figure out if this needs to be moved somewhere else
    public boolean isInputValid(String input){
        // Returns true or false based on set requirements

        // Currently we are only checking for some kind of input (Could be simplified but we'll need to add more later)
        if (input.length() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void submitIsPressed(View v) {
        // Set
        TextView answer = findViewById(R.id.answer);
        String input = answer.getText().toString();
        if (isInputValid(input)) {
            // If input is valid, slap a strange white box over the whole button!
            v.setBackgroundColor(1);
        }
    }

    //I presume these will be helpful for you guys:
    //https://stackoverflow.com/questions/26943038/how-to-make-clickable-and-editable-textfield-in-android
    //https://stackoverflow.com/questions/6274021/implement-uitextfield-properties-in-android
}