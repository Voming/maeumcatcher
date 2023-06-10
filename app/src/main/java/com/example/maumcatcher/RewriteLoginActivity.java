package com.example.maumcatcher;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RewriteLoginActivity extends AppCompatActivity {

    EditText etName;
    EditText etID;
    EditText etPass;
    EditText etAge;
    Button save;

    int version = 1;
    LoginDatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    String id, pw, name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewrite_login);

        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etPass = findViewById(R.id.etPass);
        etAge = findViewById(R.id.etAge);
        save = findViewById(R.id.save);

        //이메일은 변경하지 못하도록
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");

        etName.setText(name);
        etID.setText(id);
        etPass.setText(pw);
        etAge.setText(age);

        etID.setEnabled(false);

        helper = new LoginDatabaseOpenHelper(RewriteLoginActivity.this, LoginDatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String id = etID.getText().toString();
                String pw = etPass.getText().toString();
                Integer age = Integer.parseInt(etAge.getText().toString());

                if(id.length() == 0 ) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(RewriteLoginActivity.this, "아이디는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else if(pw.length() == 0){
                    Toast toast = Toast.makeText(RewriteLoginActivity.this, "비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else if(name.length() == 0){
                    Toast toast = Toast.makeText(RewriteLoginActivity.this, "이름은 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else if(age==NULL){
                    Toast toast = Toast.makeText(RewriteLoginActivity.this, "나이는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "UPDATE " + helper.tableName + " SET name = '" + name + "', pw = '" + pw + "' , age = '" + age + "' WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);
                helper.updateUser(database,id,pw,name,age);

                Toast toast = Toast.makeText(RewriteLoginActivity.this, "수정이 완료되었습니다. 재로그인을 해주세요.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}