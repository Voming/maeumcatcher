package com.example.maumcatcher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FeelingDB extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "varietyEmotion.db";
    private static String DB_PATH = "/data/data/com.example.maumcatcher/databases/";
    private static final String DB_TABLE = "emotion";

    //table column names
    private static final String KEY_QUES = "example";
    private static final String KEY_ANSWER = "class";

    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public FeelingDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "varietyEmotion.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.dbase = db;

    }

    //get all question in listview
    public List <FeelingQuestion> getAllQuestions(){
        List <FeelingQuestion> questionList = new ArrayList<FeelingQuestion>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE + " ORDER BY RANDOM()";
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){

            do{
                for (int i = 0; i < 5; i++) {
                    FeelingQuestion q = new FeelingQuestion();
                    q.setQuestion(cursor.getString(4));
                    q.setAnswer(cursor.getString(0));
                    q.setOption1(cursor.getString(0));
                    cursor.moveToNext();
                    q.setOption2(cursor.getString(0));
                    cursor.moveToNext();
                    q.setOption3(cursor.getString(0));

                    //add question in list
                    questionList.add(q);
                }
                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }

}
