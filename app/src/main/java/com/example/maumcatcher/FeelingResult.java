package com.example.maumcatcher;

import static com.example.maumcatcher.GuessActivity.result;
import static com.example.maumcatcher.GuessActivity.score;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FeelingResult extends AppCompatActivity {

    public ArrayList<String> AnswerList = new ArrayList<String>();
    public ArrayList<String> SelectedAnswer = new ArrayList<String>();
    public ArrayList<String> ActualAnswer = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_lose);

        TextView scoreTxtView = (TextView) findViewById(R.id.scoreTXT);
        TextView expTxtView = (TextView) findViewById(R.id.explainTXT);
        ImageView img = (ImageView)findViewById(R.id.level);

        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int score1 = sharedPreferences.getInt(result, score);

        // 점수 출력
        if(score1 == 0){
            img.setImageResource(R.drawable.level0);
            scoreTxtView.setText("0개를 맞췄어요!");
            expTxtView.setText("조금 더 학습해볼까요?");
        }else if(score1 == 1){
            img.setImageResource(R.drawable.level1);
            scoreTxtView.setText("1개를 맞췄어요!");
            expTxtView.setText("조금 더 노력해봅시다!");
        }else if(score1 == 2){
            img.setImageResource(R.drawable.level2);
            scoreTxtView.setText("2개를 맞췄어요!");
            expTxtView.setText("아쉽네요!");
        }else if(score1 == 3){
            img.setImageResource(R.drawable.level3);
            scoreTxtView.setText("3개를 맞췄어요!");
            expTxtView.setText("아쉽네요!");
        }else if(score1 == 4){
            img.setImageResource(R.drawable.level4);
            scoreTxtView.setText("4개를 맞췄어요!");
            expTxtView.setText("잘하셨습니다!");
        }else if(score1 == 5){
            img.setImageResource(R.drawable.level5);
            scoreTxtView.setText("5개를 맞췄어요!");
            expTxtView.setText("축하합니다!");
        }

        // 오답체크
        AnswerList = getIntent().getStringArrayListExtra("answerList");
        SelectedAnswer = getIntent().getStringArrayListExtra("selectedAnswer");
        ActualAnswer = getIntent().getStringArrayListExtra("actualAnswer");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FeelingResult.this, GuessActivity.class);
        startActivity(intent);
    }

    // 계속하기
    public void NewButton(View view){
        Intent intent = new Intent(getApplicationContext(), FeelingActivity.class);
        startActivity(intent);
        score=0;
    }

    // 나가기
    public void EndButton(View view){
        Intent intent = new Intent(getApplicationContext(), FeelingFragment.class);
        startActivity(intent);
        score=0;
    }

    // 오답노트
    public void checkAnswer(View view){
        Intent intent = new Intent(FeelingResult.this, GuessCheck.class);
        intent.putStringArrayListExtra("answerList", AnswerList);
        intent.putStringArrayListExtra("selectedAnswer", SelectedAnswer);
        intent.putStringArrayListExtra("actualAnswer", ActualAnswer);
        startActivity(intent);
        //finish();
    }
}
