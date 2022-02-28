package com.example.flashcards.models;

/**
 * Flash Card Model
 **/

public class FlashCardModel {
    private int id;
    private QuestionModel questions;
    private boolean flashCardAttempted;

    public FlashCardModel(int id, QuestionModel questions, boolean flashCardAttempted) {
        this.id = id;
        this.questions = questions;
        this.flashCardAttempted = flashCardAttempted;
    }

    public FlashCardModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionModel getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionModel questions) {
        this.questions = questions;
    }

    public boolean isFlashCardAttempted() {
        return flashCardAttempted;
    }

    public void setFlashCardAttempted(boolean flashCardAttempted) {
        this.flashCardAttempted = flashCardAttempted;
    }

    @Override
    public String toString() {
        return "FlashCardModel{" +
                "id=" + id +
                ", questions=" + questions +
                ", flashCardAttempted=" + flashCardAttempted +
                '}';
    }
}
