package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;
import com.example.flashcards.models.FlashCardModel;
import com.example.flashcards.models.QuizModel;

import java.util.List;
import java.util.Locale;

public class TopicSelectActivity extends AppCompatActivity {

    public DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_select);

        dbHelper = new DataBaseHelper(this);
        int value = dbHelper.getMastery();
        Log.d("Flash Card Value", "Mastery Value " + value);
        Toast.makeText(TopicSelectActivity.this, "Mastery value = " + value, Toast.LENGTH_SHORT).show();

        // if mastery_status == true where mastery_id = previous quiz/practice topic || topic = addition and mode = practice
        // android:setClickable(true)
        // else
        // false
        String[] topics = {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"};
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");

        // Activating buttons based on completion of previous items
        if (mode.equals("practice")) {


            for (String topic : topics) {

                // flashCards
                List<QuizModel> quizzes = dbHelper.getAllQuizzesWithTopicNotAttempted(topic);

                Button additionButton = findViewById(R.id.topic_addition_btn);
                additionButton.setEnabled(true);
                // Confirming completion of previous quiz for topic
                if (quizzes.size() == 0) {

                    // Comparing topic to activate button
                    switch (topic) {
                        case "SUBTRACTION":
                            Button subtractionButton = findViewById(R.id.topic_subtraction_btn);
                            subtractionButton.setEnabled(true);
                            break;
                        case "MULTIPLICATION":
                            Button multiplicationButton = findViewById(R.id.topic_multiplication_btn);
                            multiplicationButton.setEnabled(true);
                            break;
                        case "DIVISION":
                            Button divisionButton = findViewById(R.id.topic_division_btn);
                            divisionButton.setEnabled(true);
                            break;
                    }
                }
            }
        }

        // Confirming completion of previous practice for topic
        else {
            for (String topic : topics) {
                // flashCards
                List<FlashCardModel> flashCards = dbHelper.getAllFlashCardsWithTopicNotAttempted(topic);
                // Confirming completion of previous quiz for topic
                if (flashCards.size() == 0) {

                    // Comparing topic to activate button
                    switch (topic) {
                        case "ADDITION":
                            Button additionQuizButton = findViewById(R.id.topic_addition_btn);
                            additionQuizButton.setEnabled(true);
                            break;
                        case "SUBTRACTION":
                            Button subtractionButton = findViewById(R.id.topic_subtraction_btn);
                            subtractionButton.setEnabled(true);
                            break;
                        case "MULTIPLICATION":
                            Button multiplicationButton = findViewById(R.id.topic_multiplication_btn);
                            multiplicationButton.setEnabled(true);
                            break;
                        case "DIVISION":
                            Button divisionButton = findViewById(R.id.topic_division_btn);
                            divisionButton.setEnabled(true);
                            break;
                    }
                }
            }
        }
    }


    public void launchActivity(View v){
        // Switches to the mastery or practice screen via a button

        // Access the intent and retrieve the desired "mode," either practice or quiz
        Intent oldi = getIntent();
        String mode = oldi.getStringExtra("mode");

        // Set the desired topic
        String topic;
        switch (v.getId()){
            case R.id.topic_addition_btn:
                topic = "ADDITION";
                break;
            case R.id.topic_subtraction_btn:
                topic = "SUBTRACTION";
                break;
            case R.id.topic_multiplication_btn:
                topic = "MULTIPLICATION";
                break;
            case R.id.topic_division_btn:
                topic = "DIVISION";
                break;
            default:
                // Should never happen as only the four above buttons should call this
                topic = "ERROR";
                break;
        }
        // Adding sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
        mediaPlayer.start();
        // Initialize the new intent
        Intent i;
        // If we desire practice mode, launch the practice activity
        if (mode.equals("practice")){
            i = new Intent(this, PracticeActivity.class);
        }
        // If we don't want practice mode, it must be the quiz activity
        else{
            i = new Intent(this, QuizzesActivity.class);
        }

        // Before launching either quiz or practice, we pass the topic for use in that activity
        i.putExtra("topic",topic);
        startActivity(i);
    }

}