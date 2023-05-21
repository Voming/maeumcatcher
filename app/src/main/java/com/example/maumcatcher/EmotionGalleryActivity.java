package com.example.maumcatcher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class EmotionGalleryActivity extends AppCompatActivity {
    Button angry;
    Button disgust;
    Button fear;
    Button happy;
    Button neutral;
    Button sad;
    Button surprise;

    List<String> filesNameList = new ArrayList<>();
    List<Bitmap> bitList = new ArrayList<>();


    LinearLayout linear;
    LayoutInflater layoutInflater;
    View view;
    Context context;

    ScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_gallery);

        linear = findViewById(R.id.list);
        layoutInflater = LayoutInflater.from(this);
        scroll= findViewById(R.id.scroll);

        FileList("angry");

        //화면 전환 버튼
        angry = (Button)findViewById(R.id.angry);
        disgust=(Button)findViewById(R.id.disgust);
        fear=(Button)findViewById(R.id.fear);
        happy=(Button)findViewById(R.id.happy);
        neutral=(Button)findViewById(R.id.neutral);
        sad=(Button)findViewById(R.id.sad);
        surprise=(Button)findViewById(R.id.surprise);



        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.angry:
                        makeFolder("angry");
                        FileList("angry");
                        break;
                    case R.id.disgust:
                        makeFolder("disgust");
                        FileList("disgust");
                        break;
                    case R.id.fear:
                        makeFolder("fear");
                        FileList("fear");
                        break;
                    case R.id.happy:
                        makeFolder("happy");
                        FileList("happy");
                        break;
                    case R.id.neutral:
                        makeFolder("neutral");
                        FileList("neutral");
                        break;
                    case R.id.sad:
                        makeFolder("sad");
                        FileList("sad");
                        break;
                    case R.id.surprise:
                        makeFolder("surprise");
                        FileList("surprise");
                        break;
                }
            }
        };

        angry.setOnClickListener(onClickListener);
        disgust.setOnClickListener(onClickListener);
        fear.setOnClickListener(onClickListener);
        happy.setOnClickListener(onClickListener);
        neutral.setOnClickListener(onClickListener);
        sad.setOnClickListener(onClickListener);
        surprise.setOnClickListener(onClickListener);
    }

    private File makeFolder(String emotion) {
        File dir = new File("data/data/com.example.maumcatcher/" + emotion);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    public void FileList(String strFolderName)
    {
        scroll.fullScroll(ScrollView.FOCUS_UP);
        linear.removeAllViews();
        filesNameList.clear();
        bitList.clear();

        String path = "data/data/com.example.maumcatcher/" + strFolderName;

        File directory = new File(path);
        File[] files = directory.listFiles();


        if(files.length < 1){
            Toast.makeText(getApplicationContext(), "표정 따라하기를 통해\n" +
                    "갤러리에 이미지를 추가해주세요!", Toast.LENGTH_LONG).show();
        }else{
            for (int i=0; i< files.length; i++) {
                filesNameList.add(path + "/" + files[i].getName());
            }

            for (int i=0; i< files.length; i++) {
                bitList.add(pathToBitmap(filesNameList.get(i)));
            }

            for(int i = 0; i < (bitList.size()); i++) {
                view = layoutInflater.inflate(R.layout.listitem, null, false);
                //사진
                ImageView imageView = view.findViewById(R.id.image);
                imageView.setImageBitmap(bitList.get(i));

                imageView.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0,0, view.getWidth(), view.getHeight(), 40);
                    }
                });

                imageView.setClipToOutline(true);
                linear.addView(view);
            }
        }

    }

    public Bitmap pathToBitmap(String path) {
        Bitmap bitmap = null;
        try {
            File f = new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}