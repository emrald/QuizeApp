package com.trivedi.quizeapp;

import android.support.v4.app.Fragment;

/**
 * Created by TI A1 on 20-06-2017.
 */
public class DataClass extends Fragment{

    String question;
    public DataClass(){

    }
    public DataClass(int id,String answer,String question){
        this.id=id;
        this.answer=answer;
        this.question=question;
    }
     int getIdNew() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    String answer;
}
