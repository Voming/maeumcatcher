package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindSameResultActivity extends AppCompatActivity {
    Intent intent;
    int correct;
    int incorrectNum;
    int[] incorrect;

    Button exit;
    Button retry;
    Button note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_same_result);

        intent = getIntent();
        correct = intent.getIntExtra("correct", 0);
        incorrectNum = intent.getIntExtra("incorrectNum",0);
        incorrect = intent.getIntArrayExtra("incorrect");

        System.out.println("맞은개수" + correct);
        System.out.println("틀린개수" +incorrectNum);
        for (int i = 0; i < 5; i++) {
            System.out.println(incorrect[i]);
        }

        exit = findViewById(R.id.exit);
        retry = findViewById(R.id.retry);
        note = findViewById(R.id.note);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindSameEmotionActivity.class);
                startActivity(intent);
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindSameNoteActivity.class);
                intent.putExtra("correct", correct);
                intent.putExtra("incorrectNum", incorrectNum);
                intent.putExtra("incorrect", incorrect);
                startActivity(intent);
            }
        });
    }
}