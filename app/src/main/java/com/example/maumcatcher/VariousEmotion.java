package com.example.maumcatcher;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VariousEmotion extends Activity {

    /*
    private ListView listView;
    CustomListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_various_happy);

        // 리스트뷰 참조
        this.listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        // 데이터베이스 접근을 위한 DatabaseAccess 객체 생성
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getHappy();
        databaseAccess.close();

        // 어댑터 생성, 리스트 뷰에 어댑터 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);
    }
    */

    ListView listView;
    CustomListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_various_happy);

        listView = (ListView)findViewById(R.id.listView);
        displayList();
    }

    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        String sql = "SELECT * FROM emotion WHERE class = ?";
        String[] selectionArgs = { "기쁨" };
        Cursor cursor = database.rawQuery(sql, selectionArgs);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            adapter.addItemToList(cursor.getString(0),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        listView.setAdapter(adapter);
    }
}
