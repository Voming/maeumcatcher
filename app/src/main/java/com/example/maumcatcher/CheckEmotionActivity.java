package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CheckEmotionActivity extends AppCompatActivity {

    ImageView followView;

    Button angry;
    Button disgust;
    Button fear;
    Button happy;
    Button neutral;
    Button sad;
    Button surprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_emotion);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("사진");

        followView = findViewById(R.id.followView);
        angry = findViewById(R.id.angry);
        disgust = findViewById(R.id.disgust);
        fear = findViewById(R.id.fear);
        happy = findViewById(R.id.happy);
        neutral = findViewById(R.id.neutral);
        sad = findViewById(R.id.sad);
        surprise = findViewById(R.id.surprise);


        followView.setImageBitmap(bitmap);

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}