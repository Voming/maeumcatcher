package com.example.maumcatcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GuessCheck extends AppCompatActivity {

    ListView listView;
    GuessResult guessResult = new GuessResult();

    public ArrayList<String> AnswerList = new ArrayList<String>();
    public ArrayList<String> SelectedAnswer = new ArrayList<String>();
    public ArrayList<String> ActualAnswer = new ArrayList<String>();
    private ArrayList<GuessQuestion> m_parts = new ArrayList<GuessQuestion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_check);
        listView = findViewById(R.id.checklistView);

        AnswerList = guessResult.AnswerList;
        SelectedAnswer = guessResult.SelectedAnswer;
        ActualAnswer = guessResult.ActualAnswer;

        AnswerList = getIntent().getStringArrayListExtra("answerList");
        SelectedAnswer = getIntent().getStringArrayListExtra("selectedAnswer");
        ActualAnswer = getIntent().getStringArrayListExtra("actualAnswer");

        String[] strAnswer = new String[AnswerList.size()];
        String[] strSelected = new String[SelectedAnswer.size()];
        String[] strActual = new String[ActualAnswer.size()];

        strAnswer = AnswerList.toArray(strAnswer);
        strSelected = SelectedAnswer.toArray(strSelected);
        strActual = ActualAnswer.toArray(strActual);

        for(int i=0; i<strAnswer.length;i++) {
            m_parts.add(new GuessQuestion(strAnswer[i], strSelected[i],strActual[i]));
        }

        GuessListAdapter listAdapter = new GuessListAdapter(this, R.layout.guessname_list, m_parts);
        listView.setAdapter(listAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GuessCheck.this, GuessResult.class);
        startActivity(intent);
    }

}
