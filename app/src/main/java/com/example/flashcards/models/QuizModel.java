package com.example.flashcards.models;

/**
 * Quiz Model
 **/

public class QuizModel {

    private int id;
    private String Question;
    private String Answer;
    private boolean isQuizAttempted;

    public QuizModel(int id, String question, String answer, boolean isQuizAttempted) {
        this.id = id;
        Question = question;
        Answer = answer;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
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
                ", Question='" + Question + '\'' +
                ", Answer='" + Answer + '\'' +
                ", isQuizAttempted=" + isQuizAttempted +
                '}';
    }
}
