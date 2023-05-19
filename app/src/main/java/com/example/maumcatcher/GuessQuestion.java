package com.example.maumcatcher;

import java.util.List;

public class GuessQuestion {

    private int id;
    private String question;
    private String choice;
    private String answer;

    public GuessQuestion(){
        id=0;
        question = "";
        choice = "";
    }

    public GuessQuestion(String question, String choice, String answer){
        this.question = question;
        this.choice = choice;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }


    public String getChoice() {
        return choice;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
