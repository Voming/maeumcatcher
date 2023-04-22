package com.example.maumcatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmotionSubActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private PlayFragment playfragment = new PlayFragment();
    private EmotionSubFragment emotionsubfragment = new EmotionSubFragment();
    private FeelingFragment feelingfragment = new FeelingFragment();
    private MypageFragment mypagefragment = new MypageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_sub);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, emotionsubfragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setSelectedItemId(R.id.emotionItem); //선택순서 지정
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.playItem:
                    transaction.replace(R.id.frameLayout, playfragment).commitAllowingStateLoss();
                    break;
                case R.id.emotionItem:
                    transaction.replace(R.id.frameLayout, emotionsubfragment).commitAllowingStateLoss();
                    break;
                case R.id.feelingItem:
                    transaction.replace(R.id.frameLayout, feelingfragment).commitAllowingStateLoss();
                    break;
                case R.id.mypageItem:
                    transaction.replace(R.id.frameLayout, mypagefragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}