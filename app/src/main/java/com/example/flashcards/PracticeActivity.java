package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;
import com.example.flashcards.models.FlashCardModel;

import java.util.List;

public class PracticeActivity extends AppCompatActivity {
    private DataBaseHelper dbHelper;
    private List<FlashCardModel> practiceCards;
    private int currentCardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        dbHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        practiceCards = dbHelper.getAllFlashCardsWithTopic(topic);

        TextView topicView = findViewById(R.id.practice_topic);
        topicView.setText(topic);

        updateUI();
    }

    public void launchHome(View v){
        segueHome();
    }

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
        FlashCardModel currentCard = practiceCards.get(currentCardIndex);

        TextView answer = findViewById(R.id.practice_answer);
        String input = answer.getText().toString();
        if (isInputValid(input)) {
            //answer.setText(currentCard.getFlashCardAnswer());
            if (input.equalsIgnoreCase(currentCard.getQuestions().getAnswer())) {
                if (currentCardIndex + 1 < practiceCards.size()) {
                    currentCardIndex += 1;
                    updateUI();
                } else {
                    segueHome();
                }
            }
        }
    }

    public int current_progress = 0;
    //Change value to max number of questions
    public int max_num_questions = 5;
    private void updateUI() {
        FlashCardModel currentCard = practiceCards.get(currentCardIndex);
        TextView total = findViewById(R.id.practice_score);
        TextView question = findViewById(R.id.practice_question);
        ProgressBar prac_progress = findViewById(R.id.practice_progress_bar);

        //Setting up progress bar to start from 0
        prac_progress.setProgress(current_progress);

        //Setting up progress bar to have a max of total number of questions
        prac_progress.setMax(max_num_questions);

        total.setText(String.format("Question %d / %d", currentCardIndex + 1, practiceCards.size()));
        question.setText(currentCard.getQuestions().getQuestion());

        //Adding 1 each time this class is called so the progress bar increases each time
        current_progress ++;
    }

    private void segueHome() {
        //If user has attended all 5 questions in a section, then Mastery status will be added 20 more points.
        // For mastery maximum value will be 100.
        if(currentCardIndex == 4){
            int status = dbHelper.getMastery();
            if(status <= 80) {
                dbHelper.updateMastery(status + 20);
            }
        }
        // Adding sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
        mediaPlayer.start();

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}