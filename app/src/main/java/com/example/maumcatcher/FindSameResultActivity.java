package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    String id, pw,name,age;
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
        setContentView(R.layout.activity_find_same_result);

        intent = getIntent();
        correct = intent.getIntExtra("correct", 0);
        incorrectNum = intent.getIntExtra("incorrectNum",0);
        incorrect = intent.getIntArrayExtra("incorrect");
        id = intent.getStringExtra("id");


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


        helper = new LoginDatabaseOpenHelper(FindSameResultActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
        cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){
            bfAvg = cursor.getString(5);
            System.out.println("bfAvg = " + bfAvg);
        }



        if(correct == 0){
            level.setImageResource(R.drawable.level0);
            confidence.setText("0개를 맞췄어요");
            extxt.setText("조금 더 노력해볼까요?");

            dbAvg = 0;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);

        }else if (correct == 1) {
            level.setImageResource(R.drawable.level1);
            confidence.setText("1개를 맞췄어요");
            extxt.setText("아쉬워요");

            dbAvg = 20;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);
        }else if (correct == 2) {
            level.setImageResource(R.drawable.level2);
            confidence.setText("2개를 맞췄어요");
            extxt.setText("아쉬워요");

            dbAvg = 40;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);
        }else if (correct == 3) {
            level.setImageResource(R.drawable.level3);
            confidence.setText("3개를 맞췄어요");
            extxt.setText("잘했어요");

            dbAvg = 60;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);
        }else if (correct == 4) {
            level.setImageResource(R.drawable.level4);
            confidence.setText("4개를 맞췄어요");
            extxt.setText("대단해요");

            dbAvg = 80;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);
        }else if (correct == 5) {
            level.setImageResource(R.drawable.level5);
            confidence.setText("5개를 맞췄어요");
            extxt.setText("축하해요!");
            note.setVisibility(View.GONE);

            dbAvg = 100;
            if(Float.valueOf(bfAvg) != null){
                dbAvg = (Float.valueOf(bfAvg) + dbAvg) /2;
                System.out.println("수정됨 = " + dbAvg);
            }
            sql =  "UPDATE " + helper.tableName + " SET FeelingAvg = '" + dbAvg +"' WHERE id = '" + id + "'";
            cursor = database.rawQuery(sql, null);
            helper.updateFeelingAvgUser(database,id, dbAvg);
        }


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new LoginDatabaseOpenHelper(FindSameResultActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
                database = helper.getWritableDatabase();

                sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                while(cursor.moveToNext()){
                    pw = cursor.getString(4);
                    name = cursor.getString(5);
                    age = cursor.getString(6);
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pw", pw);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
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