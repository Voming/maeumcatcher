package com.example.maumcatcher;

import static com.example.maumcatcher.GuessActivity.result;
import static com.example.maumcatcher.GuessActivity.score;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;

public class GuessResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_lose);

        TextView scoreTxtView = (TextView) findViewById(R.id.scoreTXT);
        TextView expTxtView = (TextView) findViewById(R.id.explainTXT);
        ImageView img = (ImageView)findViewById(R.id.circle_image);

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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GuessResult.this, GuessActivity.class);
        startActivity(intent);
    }

    // 계속하기
    public void NewButton(View view){
        Intent intent = new Intent(getApplicationContext(), GuessActivity.class);
        startActivity(intent);
        score=0;
    }

    // 나가기
    public void EndButton(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        score=0;
    }

    // 오답노트
    public void checkAnswer(View view){
        Intent intent = new Intent(getApplicationContext(), GuessCheck.class);
        startActivity(intent);
    }
}
