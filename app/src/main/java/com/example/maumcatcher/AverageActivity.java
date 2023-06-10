package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AverageActivity extends AppCompatActivity {

    String id, pw, name, age;

    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    String Avg1, Avg2, Avg3;

    TextView game1, game2, game3;

    Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");

        System.out.println("id = " +id);

        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);
        exit = findViewById(R.id.exit);

        helper = new LoginDatabaseOpenHelper(AverageActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
        cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){
            Avg1 = cursor.getString(4);
            System.out.println("Avg1 = " + Avg1);
            Avg2 = cursor.getString(5);
            System.out.println("Avg2 = " + Avg2);
            Avg3 = cursor.getString(6);
            System.out.println("Avg3v = " + Avg3);
        }

        game1.setText(Avg1.toString() + "%");
        game2.setText(Avg2.toString() + "%");
        //game3.setText(Avg3.toString() + "%");  //개발되면 수정

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
}