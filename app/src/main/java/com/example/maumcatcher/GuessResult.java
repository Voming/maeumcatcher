package com.example.maumcatcher;

import static com.example.maumcatcher.GuessActivity.result;
import static com.example.maumcatcher.GuessActivity.score;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;
import java.util.ArrayList;

public class GuessResult extends AppCompatActivity {

    public ArrayList<String> AnswerList = new ArrayList<String>();
    public ArrayList<String> SelectedAnswer = new ArrayList<String>();
    public ArrayList<String> ActualAnswer = new ArrayList<String>();

    String id;
    float dbAvg;
    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;
    String bfAvg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname_lose);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        System.out.println("id = " +id);

        TextView scoreTxtView = (TextView) findViewById(R.id.scoreTXT);
        TextView expTxtView = (TextView) findViewById(R.id.explainTXT);
        ImageView img = (ImageView)findViewById(R.id.level);

        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int score1 = sharedPreferences.getInt(result, score);


        helper = new LoginDatabaseOpenHelper(GuessResult.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
        cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){
            bfAvg = cursor.getString(4);
            System.out.println("bfAvg = " + bfAvg);
        }



        // 점수 출력
        if(score1 == 0){
            img.setImageResource(R.drawable.level0);
            scoreTxtView.setText("0개를 맞췄어요!");
            expTxtView.setText("조금 더 학습해볼까요?");

            dbAvg = 0;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);

        }else if(score1 == 1){
            img.setImageResource(R.drawable.level1);
            scoreTxtView.setText("1개를 맞췄어요!");
            expTxtView.setText("조금 더 노력해봅시다!");

            dbAvg = 20;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);

        }else if(score1 == 2){
            img.setImageResource(R.drawable.level2);
            scoreTxtView.setText("2개를 맞췄어요!");
            expTxtView.setText("아쉽네요!");

            dbAvg = 40;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);

        }else if(score1 == 3){
            img.setImageResource(R.drawable.level3);
            scoreTxtView.setText("3개를 맞췄어요!");
            expTxtView.setText("아쉽네요!");

            dbAvg = 60;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);

        }else if(score1 == 4){
            img.setImageResource(R.drawable.level4);
            scoreTxtView.setText("4개를 맞췄어요!");
            expTxtView.setText("잘하셨습니다!");

            dbAvg = 80;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);

        }else if(score1 == 5){
            img.setImageResource(R.drawable.level5);
            scoreTxtView.setText("5개를 맞췄어요!");
            expTxtView.setText("축하합니다!");

            dbAvg = 100;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FindAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFindAvgUser(database,id, dbAvg);
        }

        // 오답체크
        AnswerList = getIntent().getStringArrayListExtra("answerList");
        SelectedAnswer = getIntent().getStringArrayListExtra("selectedAnswer");

        ActualAnswer = getIntent().getStringArrayListExtra("actualAnswer");

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
        Intent intent = new Intent(GuessResult.this, GuessCheck.class);
        intent.putStringArrayListExtra("answerList", AnswerList);
        intent.putStringArrayListExtra("selectedAnswer", SelectedAnswer);
        intent.putStringArrayListExtra("actualAnswer", ActualAnswer);
        startActivity(intent);
        //finish();
    }
}
