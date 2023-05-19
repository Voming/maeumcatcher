package com.example.maumcatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
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
    TextView scoreno;
    TextView quesno;

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
            scoreno.setText(" "+score);
        }
        if(quid<6){
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }else{
            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();

            Intent intent = new Intent(GuessActivity.this, GuessResult.class);
            startActivity(intent);
            finish();
        }


        /*
        if(currentQuestion.getAnswer().equals(check.getText())){
            // 답 맞췄을 때
            score++;
            Log.d("Score", "Your score: "+score);
            scoreno.setText(" "+score);
        }
        if(quid<6){
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }else{
            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();

            Intent intent = new Intent(GuessActivity.this, GuessResult.class);
            startActivity(intent);
            finish();
        }
        */
    }

    public void highscore(View v){
        Intent intent = new Intent(GuessActivity.this, GuessResult.class);
        startActivity(intent);
        finish();
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
    //리스트에 선택된 답 담아서 id ++ 해야 하나?

}
