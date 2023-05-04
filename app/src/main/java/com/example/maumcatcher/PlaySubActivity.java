package com.example.maumcatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlaySubActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_various);
//        Button moveButton=findViewById(R.id.happy);
//        moveButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), VariousEmotion.class);
//                startActivity(intent);
//            }
//        });
    }

    public void ClickButton1(View v){
        Intent intent = new Intent(getApplicationContext(), VariousEmotion.class);
        startActivity(intent);
    }

    public void ClickButton2(View v){
        Intent intent = new Intent(getApplicationContext(), VariousEmotionAngry.class);
        startActivity(intent);
    }

    public void ClickButton3(View v){
        Intent intent = new Intent(getApplicationContext(), VariousEmotionDisgust.class);
        startActivity(intent);
    }

    public void ClickButton4(View v){
        Intent intent = new Intent(getApplicationContext(), VariousEmotionSad.class);
        startActivity(intent);
    }

    public void ClickButton5(View v){
        Intent intent = new Intent(getApplicationContext(), VariousEmotionFear.class);
        startActivity(intent);
    }

}
