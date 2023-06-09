package com.example.maumcatcher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class FollowFacesActivity extends AppCompatActivity {
    ImageView faceView;
    ImageView followView;
    String emotion;


    static final int REQUEST_IMAGE_CODE = 101;
    int[] img = {R.drawable.angry_1, R.drawable.angry_2, R.drawable.angry_3, R.drawable.angry_4, R.drawable.angry_5,
            R.drawable.angry_6, R.drawable.disgust_1, R.drawable.disgust_2, R.drawable.disgust_3, R.drawable.disgust_4,
            R.drawable.disgust_5, R.drawable.disgust_6, R.drawable.disgust_7, R.drawable.disgust_8, R.drawable.disgust_9,
            R.drawable.fear_1, R.drawable.fear_2, R.drawable.fear_3, R.drawable.fear_4, R.drawable.happy_1,
            R.drawable.happy_2, R.drawable.happy_3, R.drawable.happy_4, R.drawable.happy_5, R.drawable.happy_6,
            R.drawable.happy_7, R.drawable.happy_8, R.drawable.happy_9, R.drawable.happy_10, R.drawable.happy_11,
            R.drawable.happy_12, R.drawable.happy_13, R.drawable.happy_14, R.drawable.happy_15, R.drawable.neutral_1,
            R.drawable.neutral_2, R.drawable.neutral_3, R.drawable.neutral_4, R.drawable.neutral_5, R.drawable.neutral_6,
            R.drawable.neutral_7, R.drawable.neutral_8, R.drawable.neutral_9, R.drawable.neutral_10, R.drawable.sad_1,
            R.drawable.sad_2, R.drawable.sad_3, R.drawable.sad_4, R.drawable.sad_5, R.drawable.sad_6,
            R.drawable.sad_7, R.drawable.sad_8, R.drawable.sad_9, R.drawable.surprise_1, R.drawable.surprise_2,
            R.drawable.surprise_3, R.drawable.surprise_4, R.drawable.surprise_5, R.drawable.surprise_6, R.drawable.surprise_7,
            R.drawable.surprise_8, R.drawable.surprise_9, R.drawable.surprise_10
    };
    int num;

    TextView textView2;
    Button camera;
    Button retry;
    Button review;

    Bitmap imageBitmap;

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_faces);

        faceView = (ImageView)findViewById(R.id.faceView);
        followView = (ImageView)findViewById(R.id.followView);
        camera = findViewById(R.id.camera);
        retry = findViewById(R.id.retry);
        review = findViewById(R.id.review);
        textView2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Random ram = new Random();
        num = ram.nextInt(img.length);

        faceView.setBackgroundResource(img[num]);



        if(num<=5){
            emotion = "angry";
        }
        else if(num<=14){
            emotion = "disgust";
        }
        else if(num<=18){
            emotion = "fear";
        }
        else if(num<=33){
            emotion = "happy";
        }
        else if(num<=43){
            emotion = "neutral";
        }
        else if(num<=52){
            emotion = "sad";
        }
        else{
            emotion = "surprise";
        }



        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
                faceView.setVisibility(View.GONE);
                followView.setVisibility(View.VISIBLE);
                retry.setVisibility(View.VISIBLE);
                review.setVisibility(View.VISIBLE);

                camera.setText("다시 찍기");
                textView2.setText("잘 따라 했는지 확인해 볼까요?");
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckEmotionActivity.class);
                intent.putExtra("사진", imageBitmap);
                intent.putExtra("감정", emotion);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FollowFacesActivity.this);

                v = LayoutInflater.from(FollowFacesActivity.this).inflate(
                        R.layout.follow_custom_dialog,
                        (LinearLayout)findViewById(R.id.layoutDialog));

               //이미지 설정
                builder.setView(v);
                ((ImageView)v.findViewById(R.id.image)).setImageResource(img[num]);

                AlertDialog alertDialog = builder.create();

                v.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog. dismiss();
                    }
                });

                alertDialog.show();
            }

        });


    }

    public void takePic(){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(imageTakeIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(imageTakeIntent, REQUEST_IMAGE_CODE);
        }


    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            followView.setImageBitmap(imageBitmap);
            System.out.print(emotion);
        }

    }
}