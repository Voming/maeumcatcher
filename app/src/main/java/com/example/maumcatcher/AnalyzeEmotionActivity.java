package com.example.maumcatcher;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.stream.IntStream;


public class AnalyzeEmotionActivity extends AppCompatActivity {


    ImageView level;
    TextView confidence_view;
    Button exit;
    Button retry;
    Button save;
    Button gallery;
    TextView save_txt;

    String FaceValue;
    String value;
    String confidence;

    Bitmap bitmap;
    String emotion;

    String setLv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_emotion);

        Intent intent = getIntent();
        bitmap = intent.getParcelableExtra("사진");
        emotion = intent.getStringExtra("감정");


        level = findViewById(R.id.level);
        confidence_view = findViewById(R.id.confidence);
        exit = findViewById(R.id.exit);
        retry = findViewById(R.id.retry);
        save = findViewById(R.id.save);
        gallery = findViewById(R.id.gallery);
        save_txt = findViewById(R.id.save_txt);


        SaveBitmapToFileCache(bitmap, "data/data/com.example.maumcatcher/test.jpg");

        new Thread(() -> {
            apiStart(); // network 동작, 인터넷에서 xml을 받아오는 코드

            //api 감정 재분류
            if(Objects.equals(value, "laugh") || Objects.equals(value, "smile")){
                value = "happy";
            }

            System.out.println("원래 감정" + emotion);
            System.out.println("분석한 감정" + value);

            if(Objects.equals(value, emotion)){
                if(parseInt(confidence) <=  40.0){
                    setLv = "level1";
                }else  if(parseInt(confidence) <=  60.0){
                    setLv = "level2";
                }else  if(parseInt(confidence) <=  70.0){
                    setLv = "level3";
                }else  if(parseInt(confidence) <=  80.0){
                    setLv = "level4";
                }else  if(parseInt(confidence) <=  90.0){
                    setLv = "level5";
                }
            }
            else{
                setLv = "level0";
            }
        }).start();

        System.out.println("원래 감정" + emotion);
        System.out.println("분석한 감정" + value);

        if(setLv =="level0"){
            level.setImageResource(R.drawable.level0);
            confidence_view.setText("같은 표정이 아닙니다.");
            save_txt.setVisibility(View.GONE);
        }else if(setLv =="level1"){
            level.setImageResource(R.drawable.level1);
        }else if(setLv =="level2"){
            level.setImageResource(R.drawable.level2);
        }else if(setLv =="level3"){
            level.setImageResource(R.drawable.level3);
        }else if(setLv =="level4"){
            level.setImageResource(R.drawable.level4);
        }else if(setLv =="level5"){
            level.setImageResource(R.drawable.level5);
        }




//버튼이벤트------------------------------------------------------------------
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FollowFacesActivity.class);

                startActivity(intent);
            }
        });

        long now = System.currentTimeMillis(); // 현재시간 받아오기
        Date date = new Date(now); // Date 객체 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);

        String nowTime1 = nowTime.replace("-","_");
        String nowTime2 = nowTime1.replace(":","_");
        String nowTime3 = nowTime2.replace(" ","_");
        System.out.println(nowTime3);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdPath = "data/data/com.example.maumcatcher/" + emotion;
                File saveFile = new File(sdPath);

                OutputStream out = null;

                try{
                    if(!saveFile.isDirectory()){
                        saveFile.mkdirs();
                    }
                    saveFile.createNewFile();
                    out = new FileOutputStream("data/data/com.example.maumcatcher/" + emotion + "/" + nowTime3 + ".jpg");

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.close();

                }catch(FileNotFoundException exception){
                    Log.e("FileNotFoundException", exception.getMessage());
                }catch(IOException exception){
                    Log.e("IOException", exception.getMessage());
                }

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(getApplicationContext(), EmotionGalleryActivity.class);
=======
                Intent intent = new Intent(getApplicationContext(), CheckEmotionActivity.class);
>>>>>>> origin/HannaKim

                startActivity(intent);
            }
        });

    } //-------------------------------------------------------------------------------------------------



    //bitmap 파일로 변화하여 api 분석
    private void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath){
        File fileCacheItem = new File(strFilePath);
        OutputStream out = null;

        try
        {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //api 사용
    public  void apiStart(){
        StringBuffer reqStr = new StringBuffer();
        String clientId = "3qYE8xxUtVid9KvmGwFU";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "VfTut99XLW";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile ="data/data/com.example.maumcatcher/test.jpg";
            File uploadFile = new File(imgFile);
            //String apiURL = "https://openapi.naver.com/v1/vision/celebrity"; // 유명인 얼굴 인식
            String apiURL = "https://openapi.naver.com/v1/vision/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());


                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray faces = jsonObject.getJSONArray("faces");
                IntStream.range(0, faces.length()).boxed().forEach(i -> {
                    try {
                        System.out.println(faces.getJSONObject(i).getString("emotion"));
                        FaceValue = faces.getJSONObject(i).getString("emotion");

                        JSONObject FacejsonObject = new JSONObject(FaceValue);
                        value = FacejsonObject.getString("value");
                        confidence =  FacejsonObject.getString("confidence");

                        System.out.println(value + confidence);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}