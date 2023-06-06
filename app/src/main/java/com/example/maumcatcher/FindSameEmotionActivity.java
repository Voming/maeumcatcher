package com.example.maumcatcher;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class FindSameEmotionActivity extends AppCompatActivity {

    /*int[] q1 = {R.drawable.angry_1, R.drawable.angry_2, R.drawable.happy_14, R.drawable.fear_4};
    int[] q2 = {R.drawable.angry_3, R.drawable.angry_4, R.drawable.neutral_3, R.drawable.sad_7};
    int[] q3 = {R.drawable.angry_5, R.drawable.angry_6, R.drawable.fear_3, R.drawable.happy_4};

    int[] q4 = {R.drawable.disgust_1, R.drawable.disgust_2, R.drawable.happy_3, R.drawable.fear_3};
    int[] q5 = {R.drawable.disgust_3, R.drawable.disgust_4, R.drawable.surprise_1, R.drawable.fear_1};
    int[] q6 = {R.drawable.disgust_5, R.drawable.disgust_6, R.drawable.neutral_2, R.drawable.sad_1};
    int[] q7 = {R.drawable.disgust_7, R.drawable.disgust_8, R.drawable.surprise_3, R.drawable.sad_2};

    int[] q8 = {R.drawable.fear_1, R.drawable.fear_2, R.drawable.happy_3, R.drawable.surprise_6};
    int[] q9 = {R.drawable.fear_3, R.drawable.fear_4, R.drawable.sad_5, R.drawable.neutral_5};

    int[] q10 = {R.drawable.happy_1, R.drawable.happy_2, R.drawable.neutral_8, R.drawable.angry_1};
    int[] q11 = {R.drawable.happy_3, R.drawable.happy_4, R.drawable.fear_3, R.drawable.fear_4};
    int[] q12 = {R.drawable.happy_5, R.drawable.happy_6, R.drawable.angry_5, R.drawable.neutral_4};
    int[] q13 = {R.drawable.happy_7, R.drawable.happy_8, R.drawable.disgust_5, R.drawable.sad_7};
    int[] q14 = {R.drawable.happy_9, R.drawable.happy_10, R.drawable.surprise_10, R.drawable.disgust_1};

    int[] q15 = {R.drawable.neutral_1, R.drawable.neutral_2, R.drawable.happy_5, R.drawable.angry_1};
    int[] q16 = {R.drawable.neutral_3, R.drawable.neutral_4, R.drawable.fear_3, R.drawable.surprise_6};
    int[] q17 = {R.drawable.neutral_5, R.drawable.neutral_6, R.drawable.happy_14, R.drawable.sad_9};
    int[] q18 = {R.drawable.neutral_7, R.drawable.neutral_8, R.drawable.disgust_5, R.drawable.sad_7};
    int[] q19 = {R.drawable.neutral_9, R.drawable.neutral_10, R.drawable.surprise_10, R.drawable.disgust_1};

    int[] q20 = {R.drawable.sad_1, R.drawable.sad_2, R.drawable.surprise_9, R.drawable.angry_1};
    int[] q21 = {R.drawable.sad_3, R.drawable.sad_4, R.drawable.fear_3, R.drawable.surprise_2};
    int[] q22 = {R.drawable.sad_5, R.drawable.sad_6, R.drawable.happy_14, R.drawable.neutral_4};
    int[] q23 = {R.drawable.sad_7, R.drawable.sad_8, R.drawable.disgust_5, R.drawable.surprise_7};

    int[] q24 = {R.drawable.surprise_1, R.drawable.surprise_2, R.drawable.angry_6, R.drawable.neutral_9};
    int[] q25 = {R.drawable.surprise_3, R.drawable.surprise_4, R.drawable.neutral_8, R.drawable.happy_11};
    int[] q26 = {R.drawable.surprise_5, R.drawable.surprise_6, R.drawable.happy_14, R.drawable.sad_9};
    int[] q27 = {R.drawable.surprise_7, R.drawable.surprise_8, R.drawable.disgust_5, R.drawable.sad_7};
    int[] q28 = {R.drawable.surprise_9, R.drawable.surprise_10, R.drawable.angry_3, R.drawable.disgust_1};

     */

    //20개의 문제 랜덤으로 출력
    int[][] question = {
            {R.drawable.angry_1, R.drawable.angry_2, R.drawable.happy_14, R.drawable.fear_4},         //01-0
            {R.drawable.angry_3, R.drawable.angry_4, R.drawable.neutral_3, R.drawable.sad_7},         //01-1
            {R.drawable.fear_3, R.drawable.angry_5, R.drawable.angry_6, R.drawable.happy_4},          //12-2
            {R.drawable.happy_3, R.drawable.fear_3,R.drawable.disgust_1, R.drawable.disgust_2},       //23-3
            {R.drawable.disgust_3, R.drawable.surprise_1, R.drawable.fear_1, R.drawable.disgust_4},   //03-4
            {R.drawable.disgust_5, R.drawable.neutral_2, R.drawable.disgust_6, R.drawable.sad_1},     //02-5
            {R.drawable.fear_1, R.drawable.fear_2, R.drawable.happy_3, R.drawable.surprise_6},        //01-6
            {R.drawable.sad_5, R.drawable.neutral_5, R.drawable.fear_3, R.drawable.fear_4},           //23-7
            { R.drawable.neutral_8, R.drawable.happy_1, R.drawable.happy_2, R.drawable.angry_1},      //12-8
            {R.drawable.happy_3, R.drawable.happy_4, R.drawable.fear_3, R.drawable.fear_4},           //01-9
            {R.drawable.happy_5, R.drawable.angry_5, R.drawable.neutral_4, R.drawable.happy_6},       //03-10
            {R.drawable.happy_5, R.drawable.angry_1, R.drawable.neutral_1, R.drawable.neutral_2},     //23-11
            {R.drawable.neutral_3, R.drawable.fear_3, R.drawable.surprise_6, R.drawable.neutral_4},   //03-12
            {R.drawable.neutral_5, R.drawable.happy_14, R.drawable.neutral_6, R.drawable.sad_9},      //02-13
            {R.drawable.sad_1, R.drawable.sad_2, R.drawable.surprise_9, R.drawable.angry_1},          //01-14
            {R.drawable.fear_3, R.drawable.surprise_2, R.drawable.sad_3, R.drawable.sad_4},           //23-15
            {R.drawable.sad_5, R.drawable.happy_14, R.drawable.neutral_4, R.drawable.sad_6},          //13-16
            {R.drawable.surprise_1, R.drawable.surprise_2, R.drawable.angry_6, R.drawable.neutral_9}, //01-17
            {R.drawable.surprise_3, R.drawable.neutral_8, R.drawable.surprise_4, R.drawable.happy_11},//02-18
            { R.drawable.angry_3, R.drawable.surprise_9, R.drawable.surprise_10,R.drawable.disgust_1} //12-19
    };



    ImageButton img1;
    ImageButton img2;
    ImageButton img3;
    ImageButton img4;
    TextView qNum;

    boolean ck0 = false;
    boolean ck1 = false;
    boolean ck2 = false;
    boolean ck3 = false;

    int[] numArray = new int[5];
    int[] incorrect = new int[5]; //오답노트 문제 번호 저장

    int ckCount = 0; //선택한 개수
    int correct = 0;  //맞은 개수
    int incorrectNum = 0;
    int questionNum = 0; //질문 위치번호

    // 타이머
    private static final long COUNTDOWN_IN_MILLIS = 300000;
    private TextView CountDown;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_same_emotion);

        CountDown = (TextView)findViewById(R.id.time_txt);
        textColorDefaultCd = CountDown.getTextColors();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        qNum = findViewById(R.id.qNum);

        Random random = new Random();
        //문제 랜덤으로 출력
        for(int i = 0; i <5; i++){
            numArray[i] = random.nextInt(20);
            for(int j = 0; j < i; j++){
                if(numArray[i] == numArray[j]){
                    i--;
                }
            }
        }

        /*//질문 리스트의 랜덤번째 문제 가져옴

        rand(question[numArray[questionNum]], 4);*/

        img1.setBackgroundResource(question[numArray[questionNum]][0]);
        img2.setBackgroundResource(question[numArray[questionNum]][1]);
        img3.setBackgroundResource(question[numArray[questionNum]][2]);
        img4.setBackgroundResource(question[numArray[questionNum]][3]);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img1 :
                        if(!ck0){
                            img1.setBackgroundResource(R.drawable.check);
                            ck0 = true;
                            ckCount +=1;
                        } else{
                            img1.setBackgroundResource(question[numArray[questionNum]][0]);
                            ck0 = false;
                            ckCount -=1;
                        }break ;
                    case R.id.img2 :
                        if(!ck1){
                            img2.setBackgroundResource(R.drawable.check);
                            ck1 = true;
                            ckCount +=1;
                        } else{
                            img2.setBackgroundResource(question[numArray[questionNum]][1]);
                            ck1 = false;
                            ckCount -=1;
                        }break ;
                    case R.id.img3 :
                        if(!ck2){
                            img3.setBackgroundResource(R.drawable.check);
                            ck2 = true;
                            ckCount +=1;
                        } else{
                            img3.setBackgroundResource(question[numArray[questionNum]][2]);
                            ck2 = false;
                            ckCount -=1;
                        }break ;
                    case R.id.img4 :
                        if(!ck3){
                            img4.setBackgroundResource(R.drawable.check);
                            ck3 = true;
                            ckCount +=1;
                        } else{
                            img4.setBackgroundResource(question[numArray[questionNum]][3]);
                            ck3 = false;
                            ckCount -=1;
                        }break ;
                }

                //정답 확인
                if(ckCount ==2){
                    String real = String.valueOf(questionNum+2);
                    qNum.setText(real+ "/5");
                    if(numArray[questionNum] == 0){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 1){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 2){
                        if(ck1 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 3){
                        if(ck2 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 4){
                        if(ck0 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 5){
                        if(ck0 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 6){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 7){
                        if(ck2 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 8){
                        if(ck1 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 9){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 10){
                        if(ck0 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 11){
                        if(ck2 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 12){
                        if(ck0 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 13){
                        if(ck0 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 14){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 15){
                        if(ck2 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 16){
                        if(ck1 && ck3) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 17){
                        if(ck0 && ck1) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 18){
                        if(ck0 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }else if(numArray[questionNum] == 19){
                        if(ck1 && ck2) correct +=1;
                        else {
                            incorrect[incorrectNum] = numArray[questionNum];
                            incorrectNum += 1;
                        }
                    }

                    ck0=false;
                    ck1=false;
                    ck2=false;
                    ck3=false;

                    ckCount=0;
                    questionNum +=1;

                    if(questionNum == 5){
                        Intent intent = new Intent(getApplicationContext(), FindSameResultActivity.class);
                        intent.putExtra("correct", correct);
                        intent.putExtra("incorrectNum", incorrectNum);
                        intent.putExtra("incorrect", incorrect);
                        startActivity(intent);
                    }else {
                        //rand(question[numArray[questionNum]], 4);
                        img1.setBackgroundResource(question[numArray[questionNum]][0]);
                        img2.setBackgroundResource(question[numArray[questionNum]][1]);
                        img3.setBackgroundResource(question[numArray[questionNum]][2]);
                        img4.setBackgroundResource(question[numArray[questionNum]][3]);
                    }
                }
            }
        };

        img1.setOnClickListener(onClickListener);
        img2.setOnClickListener(onClickListener);
        img3.setOnClickListener(onClickListener);
        img4.setOnClickListener(onClickListener);

    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis=0;
                updateCountDownText();

                // 다음으로 넘어가는 함수 호출 (선택하지 못한 문제들 오답 처리, 결과창으로 이동)

                countDownTimer.cancel();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        CountDown.setText(timeFormatted);
        if(timeLeftInMillis<10000){
            CountDown.setTextColor(Color.RED);
        } else{
            CountDown.setTextColor(textColorDefaultCd);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    /* //배열 섞기
    static void rand( int array[], int a)
    {
        // Creating object for Random class
        Random rd = new Random();

        // Starting from the last element and swapping one by one.
        for (int i = a-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = rd.nextInt(i+1);

            // Swap array[i] with the element at random index
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }*/

}