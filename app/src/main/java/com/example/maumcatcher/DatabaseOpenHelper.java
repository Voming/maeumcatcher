package com.example.maumcatcher;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    // database 의 파일 경로
    // C:/Users/apf_temp_admin/AndroidStudioProjects/maeumcatcher/app/src/main/assets
    private static String DB_PATH = "/data/data/com.example.maumcatcher/databases/";
    private static final String DB_NAME = "varietyEmotion.db";
    private static final int DB_VERSION = 1;
    private Context mContext;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/com.example.maumcatcher/databases/" + context.getPackageName() + "varietyEmotion.db";
        this.mContext = context;

        dataBaseCheck();
    }

    private void dataBaseCheck() {
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            dbCopy();
        }
    }

    private void dbCopy() {
        try {
            File folder = new File(DB_PATH);
            if (!folder.exists()) {
                folder.mkdir();
            }

            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String out_filename = DB_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                outputStream.write(mBuffer,0,mLength);
            }
            outputStream.flush();;
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("dbCopy","IOException 발생함");
        }
    }

}
