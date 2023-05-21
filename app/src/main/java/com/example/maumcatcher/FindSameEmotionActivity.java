package com.example.maumcatcher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class FindSameEmotionActivity extends AppCompatActivity {
    String[] emotion = {"angry", "disgust", "fear", "happy", "neutral", "sad", "surprise"};

    int[] angry = {R.drawable.angry_1, R.drawable.angry_2, R.drawable.angry_3, R.drawable.angry_4};

    int[] disgust = {R.drawable.disgust_1, R.drawable.disgust_2, R.drawable.disgust_3, R.drawable.disgust_4,
            R.drawable.disgust_5, R.drawable.disgust_6, R.drawable.disgust_7};

    int[] fear = {R.drawable.fear_1, R.drawable.fear_2, R.drawable.fear_3, R.drawable.fear_4};

    int[] happy = {R.drawable.happy_1, R.drawable.happy_2, R.drawable.happy_3, R.drawable.happy_4, R.drawable.happy_5,
            R.drawable.happy_6, R.drawable.happy_7, R.drawable.happy_8};

    int[] neutral = {R.drawable.neutral_1, R.drawable.neutral_2, R.drawable.neutral_3, R.drawable.neutral_4, R.drawable.neutral_5};

    int[] sad = {R.drawable.sad_1, R.drawable.sad_2, R.drawable.sad_3, R.drawable.sad_4, R.drawable.sad_5, R.drawable.sad_6};

    int[] surprise = {R.drawable.surprise_1, R.drawable.surprise_2, R.drawable.surprise_3, R.drawable.surprise_4, R.drawable.surprise_5,
            R.drawable.surprise_6, R.drawable.surprise_7};

    String findEm;
    String notEm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_same_emotion);

        Random ram = new Random();
        int rand1 = ram.nextInt(7);
        if(rand1 == 0){
            findEm = "angry";
            int rand2 = ram.nextInt(6);
            if(rand2 == 0){
                notEm1 = "disgust";
                int rand3 = ram.nextInt(5);
                if(rand1 == 0){
                    findEm = "angry";
                }else if(rand1 == 0){
                    findEm = "disgust";
                }else if(rand1 == 0){
                    findEm = "fear";
                }else if(rand1 == 0){
                    findEm = "happy";
                }else if(rand1 == 0){
                    findEm = "neutral";
                }else if(rand1 == 0){
                    findEm = "sad";
                }else if(rand1 == 0){
                    findEm = "surprise";
                }
            }else if(rand2 == 1){
                notEm1 = "fear";
            }else if(rand2 == 2){
                notEm1 = "happy";
            }else if(rand2 == 3){
                notEm1 = "neutral";
            }else if(rand2 == 4){
                notEm1 = "sad";
            }else if(rand2 == 5){
                notEm1 = "surprise";
            }
        }else if(rand1 == 1){
            findEm = "disgust";
        }else if(rand1 == 2){
            findEm = "fear";
        }else if(rand1 == 3){
            findEm = "happy";
        }else if(rand1 == 4){
            findEm = "neutral";
        }else if(rand1 == 5){
            findEm = "sad";
        }else if(rand1 == 6){
            findEm = "surprise";
        }
        int rand2 = ram.nextInt(6);
        int rand3 = ram.nextInt(7);
        boolean play = false;

        if(rand1 != rand2){
            play = true;
        }else {
            play = false;
        }


        if(play){

        }

    }


}