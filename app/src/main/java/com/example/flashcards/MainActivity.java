package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;
import com.example.flashcards.models.FlashCardModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button viewAll;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

// Commenting out database code for testing, feel free to reenable if you need to use it!
/*

// Code used to get the output of the values in the database. This is only for testing.
        viewAll = findViewById(R.id.submit);
        db = new DataBaseHelper(this);
        //db.insertFlashCards();
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<FlashCardModel> flashCardModelList = db.getAllFlashCards();
                    String value = "";
                    for (FlashCardModel flashCardModel : flashCardModelList) {
                        Log.d("Flash Card Value", flashCardModel.toString());
                        value += flashCardModel.toString();
                    }
                    Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Log.d("Error Value", e.getMessage());
                }
            }
        });

    }

 */

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

    //I presume these will be helpful for you guys:
    //https://stackoverflow.com/questions/26943038/how-to-make-clickable-and-editable-textfield-in-android
    //https://stackoverflow.com/questions/6274021/implement-uitextfield-properties-in-android
}