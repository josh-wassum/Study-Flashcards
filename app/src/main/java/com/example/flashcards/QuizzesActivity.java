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
import com.example.flashcards.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class QuizzesActivity extends AppCompatActivity {

    public DataBaseHelper dbHelper;
    public List<QuizModel> quizzes = new ArrayList<>();
    public List<FlashCardModel> flashCards = new ArrayList<>();
    private int currentCardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        //Load quiz data using topic
        dbHelper = new DataBaseHelper(this);
        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");


        // flashCards
        flashCards = dbHelper.getAllFlashCardsWithTopicNotAttempted(topic);

        if(flashCards.size() == 0){

            quizzes = dbHelper.getAllQuizzesWithTopic(topic);

            TextView topicView = findViewById(R.id.quiz_topic);
            topicView.setText(topic);
            ProgressBar progress = findViewById(R.id.quiz_progress_bar);
            progress.setMax(quizzes.size());

            updateUI();

        }else{
            // Here you can add the logic for deactivating the buttons of topics
            Toast.makeText(QuizzesActivity.this, "Please Attempt the FlashCard for the topic "+topic ,Toast.LENGTH_SHORT).show();
        }

        //Set progress bar size and topic text
        TextView topicView = findViewById(R.id.quiz_topic);
        topicView.setText(topic);
        ProgressBar progress = findViewById(R.id.quiz_progress_bar);
        progress.setMax(quizzes.size());



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
        QuizModel currentCard = quizzes.get(currentCardIndex);

        TextView answer = findViewById(R.id.quiz_answer);
        String input = answer.getText().toString();
        if (isInputValid(input)) {
            dbHelper.updateQuizAttempted(currentCard.getId(), true);
            if (input.equalsIgnoreCase(currentCard.getQuestions().getAnswer())) { // if the answer is correct
                if (currentCardIndex + 1 < quizzes.size()) { //and there is another question
                    //move to the next card and update the UI
                    currentCardIndex += 1;
                    updateUI();
                } else {
                    segueHome();
                }
            }
        }
    }

    private void updateUI() {
        QuizModel currentCard = quizzes.get(currentCardIndex);
        ProgressBar progress = findViewById(R.id.quiz_progress_bar);
        TextView question = findViewById(R.id.quiz_question);

        //Update progress bar progress
        progress.setProgress(currentCardIndex);

        //Set question text
        question.setText(currentCard.getQuestions().getQuestion());
    }

//        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
//        mediaPlayer.start();
        // Returns to the home screen via a button


    private void segueHome() {
        //If user has attended all 5 questions in a section, then Mastery status will be added 20 more points.
        // For mastery maximum value will be 100.
        if(currentCardIndex == 4){
            int status = dbHelper.getMastery();
            if(status <= 80) {
                dbHelper.updateMastery(status + 20);
            }
        }
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}