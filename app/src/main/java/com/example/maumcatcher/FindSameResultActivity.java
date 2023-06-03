package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FindSameResultActivity extends AppCompatActivity {
    Intent intent;
    int correct;
    int incorrectNum;
    int[] incorrect;

    Button exit;
    Button retry;
    Button note;

    TextView confidence;
    TextView extxt;
    ImageView level;

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

        confidence = findViewById(R.id.confidence);
        extxt = findViewById(R.id.extxt);
        level = findViewById(R.id.level);

        if(correct == 0){
            level.setImageResource(R.drawable.level0);
            confidence.setText("0개를 맞췄어요");
            extxt.setText("조금 더 노력해볼까요?");
        }else if (correct == 1) {
            level.setImageResource(R.drawable.level1);
            confidence.setText("1개를 맞췄어요");
            extxt.setText("아쉬워요");
        }else if (correct == 2) {
            level.setImageResource(R.drawable.level2);
            confidence.setText("2개를 맞췄어요");
            extxt.setText("아쉬워요");
        }else if (correct == 3) {
            level.setImageResource(R.drawable.level3);
            confidence.setText("3개를 맞췄어요");
            extxt.setText("잘했어요");
        }else if (correct == 4) {
            level.setImageResource(R.drawable.level4);
            confidence.setText("4개를 맞췄어요");
            extxt.setText("대단해요");
        }else if (correct == 5) {
            level.setImageResource(R.drawable.level5);
            confidence.setText("5개를 맞췄어요");
            extxt.setText("축하해요!");
            note.setVisibility(View.GONE);
        }


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