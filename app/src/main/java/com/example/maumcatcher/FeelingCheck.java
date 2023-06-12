package com.example.maumcatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FeelingCheck extends AppCompatActivity {

    ListView listView;
    FeelingResult feelingResult = new FeelingResult();

    public ArrayList<String> AnswerList = new ArrayList<String>();
    public ArrayList<String> SelectedAnswer = new ArrayList<String>();
    public ArrayList<String> ActualAnswer = new ArrayList<String>();
    private ArrayList<GuessQuestion> m_parts = new ArrayList<GuessQuestion>();

    String id, pw, name, age;

    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_check);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");

        listView = findViewById(R.id.checklistView);
        exit = findViewById(R.id.exit);

        AnswerList = feelingResult.AnswerList;
        SelectedAnswer = feelingResult.SelectedAnswer;
        ActualAnswer = feelingResult.ActualAnswer;

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

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pw", pw);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FeelingCheck.this, FeelingResult.class);
        intent.putExtra("id", id);
        intent.putExtra("pw", pw);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        startActivity(intent);
    }

}
