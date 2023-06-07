package com.example.maumcatcher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private PlayFragment playfragment = new PlayFragment();
    private EmotionFragment emotionfragment = new EmotionFragment();
    private FeelingFragment feelingfragment = new FeelingFragment();
    private MypageFragment mypagefragment = new MypageFragment();

    String id, pw, name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getintent = getIntent();
        id = getintent.getStringExtra("id");
        pw = getintent.getStringExtra("pw");
        name = getintent.getStringExtra("name");
        age = getintent.getStringExtra("age");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, playfragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        // 최초 실행 여부를 판단 ->>>
        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst", false);

        // false일 경우 최초 실행
        if(!checkFirst){
            // 앱 최초 실행시 하고 싶은 작업
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst",true);
            editor.apply();
            finish();

            Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
            startActivity(intent);
        }

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
                    transaction.replace(R.id.frameLayout, emotionfragment).commitAllowingStateLoss();
                    break;
                case R.id.feelingItem:
                    transaction.replace(R.id.frameLayout, feelingfragment).commitAllowingStateLoss();
                    break;
                case R.id.mypageItem:
                    transaction.replace(R.id.frameLayout, mypagefragment).commitAllowingStateLoss();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("pw", pw);
                    bundle.putString("name", name);
                    bundle.putString("age", age);
                    mypagefragment.setArguments(bundle);
                    break;
            }
            return true;
        }
    }
}
