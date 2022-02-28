package com.example.flashcards.models;

/**
 * Quiz Model
 **/

public class QuizModel {

    private int id;
    private QuestionModel questions;
    private boolean isQuizAttempted;

    public QuizModel(int id, QuestionModel questions, boolean isQuizAttempted) {
        this.id = id;
        this.questions = questions;
        this.isQuizAttempted = isQuizAttempted;
    }

    public QuizModel() {
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

    public boolean isQuizAttempted() {
        return isQuizAttempted;
    }

    public void setQuizAttempted(boolean quizAttempted) {
        isQuizAttempted = quizAttempted;
    }

    @Override
    public String toString() {
        return "QuizModel{" +
                "id=" + id +
                ", questions=" + questions +
                ", isQuizAttempted=" + isQuizAttempted +
                '}';
    }
}
