package com.example.flashcards.models;

/**
 * Flash Card Model
 **/

public class FlashCardModel {
    private int id;
    private String flashCardQuestion;
    private String flashCardAnswer;
    private boolean flashCardAttempted;

    public FlashCardModel(int id, String flashCardQuestion, String flashCardAnswer, boolean flashCardAttempted) {
        this.id = id;
        this.flashCardQuestion = flashCardQuestion;
        this.flashCardAnswer = flashCardAnswer;
        this.flashCardAttempted = flashCardAttempted;
    }

    public FlashCardModel() {
    }

    @Override
    public String toString() {
        return "FlashCardModel{" +
                "id=" + id +
                ", flashCardQuestion='" + flashCardQuestion + '\'' +
                ", flashCardAnswer='" + flashCardAnswer + '\'' +
                ", flashCardAttempted=" + flashCardAttempted +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlashCardQuestion() {
        return flashCardQuestion;
    }

    public void setFlashCardQuestion(String flashCardQuestion) {
        this.flashCardQuestion = flashCardQuestion;
    }

    public String getFlashCardAnswer() {
        return flashCardAnswer;
    }

    public void setFlashCardAnswer(String flashCardAnswer) {
        this.flashCardAnswer = flashCardAnswer;
    }

    public boolean isFlashCardAttempted() {
        return flashCardAttempted;
    }

    public void setFlashCardAttempted(boolean flashCardAttempted) {
        this.flashCardAttempted = flashCardAttempted;
    }
}
