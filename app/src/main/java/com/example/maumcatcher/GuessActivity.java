package com.example.maumcatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuessActivity extends AppCompatActivity {

    //put question id into list
    List<GuessQuestion> questionList;
    int quid = 1;
    GuessQuestion currentQuestion;
    TextView txtQuestion;
    RadioButton btn1, btn2, btn3, btn4, btn5;
    ImageButton butNext;
    public static String result="highscore";
    static  int score = 0;
    TextView quesno;

    // AnswerList
    public ArrayList<String> EXanswerList = new ArrayList<String>();
    public ArrayList<String> EXselectedAnswer = new ArrayList<String>();
    public ArrayList<String> EXactualAnswer = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guessname);

        //get all question from db
        GuessDB dbHelper = new GuessDB(this);
        questionList = dbHelper.getAllQuestions();

        //random question
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);

        txtQuestion = (TextView)findViewById(R.id.exampletxt);
        btn1 = (RadioButton)findViewById(R.id.rg_btn1);
        btn2 = (RadioButton)findViewById(R.id.rg_btn2);
        btn3 = (RadioButton)findViewById(R.id.rg_btn3);
        btn4 = (RadioButton)findViewById(R.id.rg_btn4);
        btn5 = (RadioButton)findViewById(R.id.rg_btn5);
        butNext = (ImageButton)findViewById(R.id.nextBtn);
        setQuestionView();

    }

    private void setQuestionView(){
        txtQuestion.setText(currentQuestion.getQuestion());
        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar.setProgress(quid);
        progressbar.setIndeterminate(false);
        //Log.d("문제번호", String.valueOf(quid));

        quesno=(TextView)findViewById(R.id.tv_progress);
        String text = quid + " / 5";
        quesno.setText(text);

        quid++;
    }

    public void NextButton(View view){
        Log.d(currentQuestion.getAnswer(), "정답");
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton check = (RadioButton)findViewById(grp.getCheckedRadioButtonId());

        String selectedAnswer = check.getText().toString();
        if(currentQuestion.getAnswer().equals(selectedAnswer)){
            // 답 맞췄을 때
            score++;
            Log.d("Score", "Your score: "+score);
        }else{
            // 답 틀렸을 때
            EXanswerList.add(currentQuestion.getQuestion());
            Log.d("틀린문제: ", EXanswerList.toString());
            EXselectedAnswer.add(selectedAnswer);

            Log.d(selectedAnswer, "선택");
            EXactualAnswer.add(currentQuestion.getAnswer());
        }

        if(quid<6){
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }else{
            // Result 라는 이름의 기본모드 설정
            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
            // sharedPreferences를 제어할 editor 선언
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            // result, score int 형식으로 저장
            editor.putInt(result,score);
            // 최종 커밋, 저장
            editor.commit();

            Intent intent = new Intent(GuessActivity.this, GuessResult.class);
            intent.putStringArrayListExtra("answerList", EXanswerList);
            intent.putStringArrayListExtra("selectedAnswer", EXselectedAnswer);
            intent.putStringArrayListExtra("actualAnswer", EXactualAnswer);
            startActivity(intent);
            finish();
        }
    }

    /*
    public void showResult(){
        Intent intent = new Intent(GuessActivity.this, GuessResult.class);
        intent.putStringArrayListExtra("answerList", answerList);
        intent.putStringArrayListExtra("selectedAnswers", selectedAnswers);
        intent.putStringArrayListExtra("actualAnswers", actualAnswers);
        startActivity(intent);
        finish();
    }
    */

}
