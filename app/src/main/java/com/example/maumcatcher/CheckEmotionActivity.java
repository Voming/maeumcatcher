package com.example.maumcatcher;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class CheckEmotionActivity extends AppCompatActivity {

    ImageView followView;


    RadioGroup radioGroup;

    Button exit;
    Button analyze;
    String emotion_kr = "";
    String emotion = "";
    Bitmap bitmap;
    Intent intent;
    String id, pw, name, age;
    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_emotion);

        intent = getIntent();
        bitmap = intent.getParcelableExtra("사진");
        emotion = intent.getStringExtra("감정");
        id = intent.getStringExtra("id");


        System.out.print(emotion);

        followView = findViewById(R.id.followView);
        followView.setImageBitmap(bitmap);

        exit = findViewById(R.id.exit);
        analyze = findViewById(R.id.analyze);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);


        if (Objects.equals(emotion, "angry")) {
            emotion_kr = "화난 표정";
        } else if (Objects.equals(emotion, "disgust")) {
            emotion_kr = "싫은 표정";
        } else if (Objects.equals(emotion, "fear")) {
            emotion_kr = "두려운 표정";
        } else if (Objects.equals(emotion, "happy")) {
            emotion_kr = "행복한 표정";
        } else if (Objects.equals(emotion, "neutral")) {
            emotion_kr = "무표정";
        } else if (Objects.equals(emotion, "sad")) {
            emotion_kr = "슬픈 표정";
        } else if (Objects.equals(emotion, "surprise")) {
            emotion_kr = "놀란 표정";
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new LoginDatabaseOpenHelper(CheckEmotionActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
                database = helper.getWritableDatabase();

                sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                while(cursor.moveToNext()){
                    pw = cursor.getString(1);
                    name = cursor.getString(2);
                    age = cursor.getString(3);
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pw", pw);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                startActivity(intent);
            }
        });
        analyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnalyzeEmotionActivity.class);
                intent.putExtra("사진", bitmap);
                intent.putExtra("감정", emotion);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            System.out.println(emotion);
            if(i == R.id.angry){
                if (Objects.equals(emotion, "angry")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.disgust){
                if (Objects.equals(emotion, "disgust")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.fear){
                if (Objects.equals(emotion, "fear")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.happy){
                if (Objects.equals(emotion, "happy")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.neutral){
                if (Objects.equals(emotion, "neutral")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.sad){
                if (Objects.equals(emotion, "sad")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
            else  if(i == R.id.surprise){
                if (Objects.equals(emotion, "surprise")) {
                    Toast.makeText(CheckEmotionActivity.this,"정답입니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CheckEmotionActivity.this,"아쉽네요. \n 정답은 " + emotion_kr + "이었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}