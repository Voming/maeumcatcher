package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FindSameNoteActivity extends AppCompatActivity {
    int[][] question = { //20개의 문제
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

    Intent intent;
    int correct;
    int incorrectNum;
    int[] incorrect;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    TextView qNum;
    TextView result;
    ImageButton nextBtn;
    Button exit;
    int resultNum = 0;

    String id, pw,name,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_same_note);

        intent = getIntent();
        correct = intent.getIntExtra("correct", 0);
        incorrectNum = intent.getIntExtra("incorrectNum",0);
        incorrect = intent.getIntArrayExtra("incorrect");

        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");

        System.out.println("맞은개수" + correct);
        System.out.println("틀린개수" + incorrectNum);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        qNum = findViewById(R.id.qNum);
        result = findViewById(R.id.result);
        nextBtn = findViewById(R.id.nextBtn);
        exit = findViewById(R.id.exit);


        String real = String.valueOf(resultNum +1);
        qNum.setText(real + "/" + String.valueOf(incorrectNum));

        img1.setBackgroundResource(question[incorrect[0]][0]);
        img2.setBackgroundResource(question[incorrect[0]][1]);
        img3.setBackgroundResource(question[incorrect[0]][2]);
        img4.setBackgroundResource(question[incorrect[0]][3]);

        if(incorrect[0] == 0){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 1){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 2){
            result.setText("정답 : 2,3");
        }else if(incorrect[0] == 3){
            result.setText("정답 : 3,4");
        }else if(incorrect[0] == 4){
            result.setText("정답 : 1,4");
        }else if(incorrect[0] == 5){
            result.setText("정답 : 1,3");
        }else if(incorrect[0] == 6){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 7){
            result.setText("정답 : 3,4");
        }else if(incorrect[0] == 8){
            result.setText("정답 : 2,3");
        }else if(incorrect[0] == 9){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 10){
            result.setText("정답 : 1,4");
        }else if(incorrect[0] == 11){
            result.setText("정답 : 3,4");
        }else if(incorrect[0] == 12){
            result.setText("정답 : 1,4");
        }else if(incorrect[0] == 13){
            result.setText("정답 : 1,3");
        }else if(incorrect[0] == 14){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 15){
            result.setText("정답 : 3,4");
        }else if(incorrect[0] == 16){
            result.setText("정답 : 2,4");
        }else if(incorrect[0] == 17){
            result.setText("정답 : 1,2");
        }else if(incorrect[0] == 18){
            result.setText("정답 : 1,3");
        }else if(incorrect[0] == 19){
            result.setText("정답 : 2,3");
        }


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

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultNum +1 < incorrectNum){
                    resultNum += 1;
                } else if (resultNum +1 >= incorrectNum) {
                    Toast.makeText(FindSameNoteActivity.this,"모든 문제를 확인했어요!", Toast.LENGTH_LONG).show();
                }
                String real = String.valueOf(resultNum +1);
                qNum.setText(real + "/" + String.valueOf(incorrectNum));

                if(incorrect[resultNum] == 0){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 1){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 2){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 2,3");
                }else if(incorrect[resultNum] == 3){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 3,4");
                }else if(incorrect[resultNum] == 4){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,4");
                }else if(incorrect[resultNum] == 5){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,3");
                }else if(incorrect[resultNum] == 6){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 7){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 3,4");
                }else if(incorrect[resultNum] == 8){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 2,3");
                }else if(incorrect[resultNum] == 9){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 10){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,4");
                }else if(incorrect[resultNum] == 11){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 3,4");
                }else if(incorrect[resultNum] == 12){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,4");
                }else if(incorrect[resultNum] == 13){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,3");
                }else if(incorrect[resultNum] == 14){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 15){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 3,4");
                }else if(incorrect[resultNum] == 16){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 2,4");
                }else if(incorrect[resultNum] == 17){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,2");
                }else if(incorrect[resultNum] == 18){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 1,3");
                }else if(incorrect[resultNum] == 19){
                    img1.setBackgroundResource(question[incorrect[resultNum]][0]);
                    img2.setBackgroundResource(question[incorrect[resultNum]][1]);
                    img3.setBackgroundResource(question[incorrect[resultNum]][2]);
                    img4.setBackgroundResource(question[incorrect[resultNum]][3]);
                    result.setText("정답 : 2,3");
                }

            }
        });
    }
}