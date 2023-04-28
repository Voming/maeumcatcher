package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class FollowFacesActivity extends AppCompatActivity {
    ImageView faceView;
    String emotion;
    int[] img = {R.drawable.angry_1, R.drawable.angry_2, R.drawable.angry_3, R.drawable.angry_4, R.drawable.disgust_1,
            R.drawable.disgust_2, R.drawable.disgust_3, R.drawable.disgust_4, R.drawable.disgust_5, R.drawable.disgust_6,
            R.drawable.disgust_7, R.drawable.fear_1, R.drawable.fear_2, R.drawable.fear_3, R.drawable.fear_4,
            R.drawable.happy_1, R.drawable.happy_2, R.drawable.happy_3, R.drawable.happy_4, R.drawable.happy_5,
            R.drawable.happy_6, R.drawable.happy_7, R.drawable.happy_8, R.drawable.neutral_1, R.drawable.neutral_2,
            R.drawable.neutral_3, R.drawable.neutral_4, R.drawable.neutral_5, R.drawable.sad_1, R.drawable.sad_2,
            R.drawable.sad_3, R.drawable.sad_4, R.drawable.sad_5, R.drawable.sad_6, R.drawable.surprise_1,
            R.drawable.surprise_2, R.drawable.surprise_3, R.drawable.surprise_4, R.drawable.surprise_5, R.drawable.surprise_6,
            R.drawable.surprise_7
    };

    Button camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_faces);

        faceView = (ImageView)findViewById(R.id.faceView);
        camera = findViewById(R.id.camera);

        Random ram = new Random();
        int num = ram.nextInt(img.length);

        faceView.setBackgroundResource(img[num]);
        System.out.println(num);

        if(num<=3){
            emotion = "angry";
        }
        else if(4<=num && num<=10){
            emotion = "disgust";
        }
        else if(11<=num && num<=14){
            emotion = "fear";
        }
        else if(15<=num && num<=22){
            emotion = "happy";
        }
        else if(23<=num && num<=27){
            emotion = "disgust";
        }
        else if(28<=num && num<=32){
            emotion = "neutral";
        }
        else if(33<=num && num<=38){
            emotion = "sad";
        }
        else{
            emotion = "surprise";
        }
        System.out.println(emotion);

    }
}