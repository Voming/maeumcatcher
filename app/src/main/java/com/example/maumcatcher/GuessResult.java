package com.example.maumcatcher;

import static com.example.maumcatcher.GuessActivity.result;
import static com.example.maumcatcher.GuessActivity.score;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;

public class GuessResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_win);

        //TextView scoreTxtView = (TextView) findViewById(R.id.txt1);
        //RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        //ImageView img = (ImageView)findViewById(R.id.img1);

        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int score1 = sharedPreferences.getInt(result, score);

//      Bundle b = getIntent().getExtras();
//      int score = b.getInt("score");
        //ratingBar.setRating(score1);
        //scoreTxtView.setText(String.valueOf(score1));

        /*
        if(score1 == 0){
            img.setImageResource(R.drawable.score_0);
        }else if(score1 == 1){
            img.setImageResource(R.drawable.score_1);
        }else if(score1 == 2){
            img.setImageResource(R.drawable.score_2);
        }else if(score1 == 3){
            img.setImageResource(R.drawable.score_3);
        }else if(score1 == 4){
            img.setImageResource(R.drawable.score_4);
        }else if(score1 == 5){
            img.setImageResource(R.drawable.score_5);
        }
        */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GuessResult.this, GuessActivity.class);
        startActivity(intent);
    }
}
