package com.example.maumcatcher;

import static java.sql.Types.NULL;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;


    EditText idEditText;
    EditText pwEditText;
    EditText nameEditText;
    EditText ageEditText;
    Button btnJoin;

    String sql;
    Cursor cursor;
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        idEditText = (EditText) findViewById(R.id.idEditText);
        pwEditText = (EditText) findViewById(R.id.pwEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);

        btnJoin = (Button) findViewById(R.id.btnJoin);

        helper = new LoginDatabaseOpenHelper(JoinActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();
                String name = nameEditText.getText().toString();
                Integer age = Integer.parseInt(ageEditText.getText().toString());

                if(id.length() == 0 ) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(JoinActivity.this, "아이디는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else if(pw.length() == 0){
                    Toast toast = Toast.makeText(JoinActivity.this, "비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else if(name.length() == 0){
                    Toast toast = Toast.makeText(JoinActivity.this, "이름은 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else if(age==NULL){
                    Toast toast = Toast.makeText(JoinActivity.this, "나이는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    //존재하는 아이디입니다.
                    Toast toast = Toast.makeText(JoinActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    helper.insertUser(database,id,pw,name,age);
                    Toast toast = Toast.makeText(JoinActivity.this, "가입이 완료되었습니다. 로그인을 해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                cursor.close();
            }
        });
    }
}