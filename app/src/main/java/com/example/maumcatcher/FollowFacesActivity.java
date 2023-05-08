package com.example.maumcatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class FollowFacesActivity extends AppCompatActivity {
    ImageView faceView;
    ImageView followView;
    String emotion;


    static final int REQUEST_IMAGE_CODE = 101;
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
    int num;

    TextView textView2;
    Button camera;
    Button retry;
    Button review;

    Bitmap imageBitmap;


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

        Random ram = new Random();
        num = ram.nextInt(img.length);

        faceView.setBackgroundResource(img[num]);

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