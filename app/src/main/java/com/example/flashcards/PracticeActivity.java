package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        practiceCards = dbHelper.getAllFlashCards();

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
            if (input.equalsIgnoreCase(currentCard.getFlashCardAnswer())) {
                if (currentCardIndex + 1 < practiceCards.size()) {
                    currentCardIndex += 1;
                    updateUI();
                } else {
                    segueHome();
                }
            }
        }
    }

    private void updateUI() {
        FlashCardModel currentCard =  practiceCards.get(currentCardIndex);
        TextView topic = findViewById(R.id.practice_topic);
        TextView total = findViewById(R.id.practice_score);
        TextView question = findViewById(R.id.practice_question);

        topic.setText("DB Helper ?'s");
        total.setText(String.format("Question %d / %d", currentCardIndex + 1, practiceCards.size()));
        question.setText(currentCard.getFlashCardQuestion());
    }

    private void segueHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}