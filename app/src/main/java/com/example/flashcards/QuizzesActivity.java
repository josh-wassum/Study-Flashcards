package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.flashcards.library.DataBaseHelper;
import com.example.flashcards.models.FlashCardModel;
import com.example.flashcards.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class QuizzesActivity extends AppCompatActivity {

    public DataBaseHelper dbHelper;
    public List<QuizModel> quizzes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        /******************************Testing for getAllQuizzes and getAllQuizWithTopic **************/
        String value = "AllFlashCard: ";
        dbHelper = new DataBaseHelper(this);
        int question_no = 4;
        QuizModel quizModel = new QuizModel();
        quizzes = dbHelper.getAllQuizzesWithTopicNotAttempted("DIVISION");

        if(quizzes.size() >= question_no){
            for(int i = 0;i<question_no;i++){
                quizModel = quizzes.get(i);
                Log.d("Question "+i, quizModel.getQuestions().getQuestion());
            }
        }else{
            Log.d("Question ", "Congrats you have attempted all quizzes");
        }

        for (QuizModel quiz : quizzes) {
            Log.d("Quiz Value", quiz.toString());
            value += quiz.toString();
        }

        //getAllQuizzesWithTopic : Possible values are "ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"
        quizzes = dbHelper.getAllQuizzesWithTopic("MULTIPLICATION");
        value += " \n Topic: MULTIPLICATION: ";
        for (QuizModel quiz : quizzes) {
            Log.d("Quiz Value", quiz.toString());
            value += quiz.toString();
        }
        Toast.makeText(QuizzesActivity.this,value,Toast.LENGTH_SHORT).show();
        /******************************************  END  *************************************/


    }

    public void launchHome(View v){
        // Returns to the home screen via a button

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}