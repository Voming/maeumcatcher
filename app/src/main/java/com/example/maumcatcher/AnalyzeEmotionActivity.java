package com.example.maumcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.stream.IntStream;

public class AnalyzeEmotionActivity extends AppCompatActivity {

    TextView txt;
    ImageView img;

    String FaceValue;
    String value;
    String confidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_emotion);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("사진");
        String emotion = intent.getStringExtra("감정");

        txt = findViewById(R.id.txt);
        img = findViewById(R.id.img);
        txt.setText(emotion);
        img.setImageBitmap(bitmap);

        SaveBitmapToFileCache(bitmap, "data/data/com.example.maumcatcher/test.jpg");

        new Thread(() -> {
            apiStart(); // network 동작, 인터넷에서 xml을 받아오는 코드
        }).start();
    }

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


    public  void apiStart(){
        StringBuffer reqStr = new StringBuffer();
        String clientId = "3qYE8xxUtVid9KvmGwFU";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "VfTut99XLW";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile ="data/data/com.example.maumcatcher/test.jpg";;
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