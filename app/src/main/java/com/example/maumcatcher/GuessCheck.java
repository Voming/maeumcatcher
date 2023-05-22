package com.example.maumcatcher;

import static com.example.maumcatcher.GuessActivity.result;
import static com.example.maumcatcher.GuessActivity.score;
//import static com.example.maumcatcher.GuessActivity.
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GuessCheck extends AppCompatActivity {

    GuessActivity guessActivity;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_check);
        listView = findViewById(R.id.checklistView);

        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int score1 = sharedPreferences.getInt(result, score);

        SharedPreferences sharedPreferences2 = getSharedPreferences("AnswerList", Context.MODE_PRIVATE);
        //ArrayList<String> check1 = sharedPreferences2.getString();
        //String check1 = sharedPreferences2.getString("AnswerList", "");
        //listView.setAdapter(check1);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GuessCheck.this, GuessResult.class);
        startActivity(intent);
    }

}
