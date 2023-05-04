package com.example.maumcatcher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VariousEmotionAngry extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_various_happy);

        // 리스트뷰 참조
        this.listView = (ListView) findViewById(R.id.listView);
        // 데이터베이스 접근을 위한 DatabaseAccess 객체 생성
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getAngry();
        databaseAccess.close();

        // 어댑터 생성, 리스트 뷰에 어댑터 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);

    }

}
