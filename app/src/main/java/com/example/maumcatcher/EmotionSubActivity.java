package com.example.maumcatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmotionSubActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private PlayFragment playfragment = new PlayFragment();
    private EmotionSubFragment emotionsubfragment = new EmotionSubFragment();
    private FeelingFragment feelingfragment = new FeelingFragment();
    private MypageFragment mypagefragment = new MypageFragment();

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_sub);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        System.out.println("id = " +id);

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
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", id);
                    System.out.println("id = " +id);
                    playfragment.setArguments(bundle1);
                    break;
                case R.id.emotionItem:
                    transaction.replace(R.id.frameLayout, emotionsubfragment).commitAllowingStateLoss();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("id", id);
                    System.out.println("id = " +id);
                    playfragment.setArguments(bundle2);
                    break;
                case R.id.feelingItem:
                    transaction.replace(R.id.frameLayout, feelingfragment).commitAllowingStateLoss();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("id", id);
                    System.out.println("id = " +id);
                    playfragment.setArguments(bundle3);
                    break;
                case R.id.mypageItem:
                    transaction.replace(R.id.frameLayout, mypagefragment).commitAllowingStateLoss();
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("id", id);
                    System.out.println("id = " +id);
                    playfragment.setArguments(bundle4);
                    break;
            }
            return true;
        }
    }
}