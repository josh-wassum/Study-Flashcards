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
    private int currentCardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        dbHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        quizzes = dbHelper.getAllQuizzesWithTopic(topic);

        TextView topicView = findViewById(R.id.quiz_topic);
        topicView.setText(topic);
        ProgressBar progress = findViewById(R.id.quiz_progress_bar);
        progress.setMax(quizzes.size());

        updateUI();

//        /******************************Testing for getAllQuizzes and getAllQuizWithTopic **************/
//        String value = "AllFlashCard: ";
//        dbHelper = new DataBaseHelper(this);
//        int question_no = 4;
//        QuizModel quizModel = new QuizModel();
//        quizzes = dbHelper.getAllQuizzesWithTopicNotAttempted("DIVISION");
//
//        if(quizzes.size() >= question_no){
//            for(int i = 0;i<question_no;i++) {
//                quizModel = quizzes.get(i);
//                Log.d("Question "+i, quizModel.getQuestions().getQuestion());
//            }
//        }else{
//            Log.d("Question ", "Congrats you have attempted all quizzes");
//        }
//
//        for (QuizModel quiz : quizzes) {
//            Log.d("Quiz Value", quiz.toString());
//            value += quiz.toString();
//        }
//
//        //getAllQuizzesWithTopic : Possible values are "ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"
//        quizzes = dbHelper.getAllQuizzesWithTopic("MULTIPLICATION");
//        value += " \n Topic: MULTIPLICATION: ";
//        for (QuizModel quiz : quizzes) {
//            Log.d("Quiz Value", quiz.toString());
//            value += quiz.toString();
//        }
//        Toast.makeText(QuizzesActivity.this,value,Toast.LENGTH_SHORT).show();
//        /******************************************  END  *************************************/


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
            if (input.equalsIgnoreCase(currentCard.getQuestions().getAnswer())) {
                if (currentCardIndex + 1 < quizzes.size()) {
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
        QuizModel currentCard = quizzes.get(currentCardIndex);
        ProgressBar progress = findViewById(R.id.quiz_progress_bar);
        TextView question = findViewById(R.id.quiz_question);

        //Setting up progress bar to start from 0
        progress.setProgress(current_progress);

        //Setting up progress bar to have a max of total number of questions
        progress.setMax(max_num_questions);
        question.setText(currentCard.getQuestions().getQuestion());

        //Adding 1 each time this class is called so the progress bar increases each time
        current_progress ++;
    }

//        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btn_sound);
//        mediaPlayer.start();
        // Returns to the home screen via a button


    private void segueHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}