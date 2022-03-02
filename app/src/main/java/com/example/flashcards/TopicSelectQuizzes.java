package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TopicSelectQuizzes extends AppCompatActivity {
    public Button topic_back_btn, add_btn, subs_btn, multi_btn, div_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_select_quizzes);



    }
}