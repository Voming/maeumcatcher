package com.example.maumcatcher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;

    EditText idEditText;
    EditText pwEditText;
    Button btnLogin;


    String sql;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = (EditText) findViewById(R.id.idEditText);
        pwEditText = (EditText) findViewById(R.id.pwEditText);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        helper = new LoginDatabaseOpenHelper(LoginActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();

                if(id.length() == 0 || pw.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                while(cursor.moveToNext()){
                    String name = cursor.getString(2);
                    String age = cursor.getString(3);

                    if(cursor.getCount() != 1){
                        //아이디가 틀렸습니다.
                        Toast toast = Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    if(!pw.equals(cursor.getString(1))){
                        //비밀번호가 틀렸습니다.
                        Toast toast = Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        //로그인성공
                        Toast toast = Toast.makeText(LoginActivity.this, "로그인성공", Toast.LENGTH_SHORT);
                        toast.show();
                        //인텐트 생성 및 호출
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("pw", pw);
                        intent.putExtra("name", name);
                        intent.putExtra("age", age);
                        startActivity(intent);
                        finish();
                    }


                }

                cursor.close();
            }
        });


    }
}
