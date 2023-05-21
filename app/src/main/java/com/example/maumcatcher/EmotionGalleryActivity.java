package com.example.maumcatcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
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

    int filesize;
    List<String> filesNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_gallery);


        final GridView gv = findViewById(R.id.gridView);
        MyGridAdapter gAdapter = new MyGridAdapter(this);

        gv.setAdapter(gAdapter);

        //디렉토리 전환 버튼
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
                        FileList("angry");
                        System.out.println("toString() 전체 출력: " + filesNameList);
                        break;
                    case R.id.disgust:
                        FileList("disgust");
                        break;
                    case R.id.fear:
                        FileList("fear");
                        break;
                    case R.id.happy:
                        FileList("happy");
                        break;
                    case R.id.neutral:
                        FileList("neutral");
                        break;
                    case R.id.sad:
                        FileList("sad");
                        break;
                    case R.id.surprise:
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

    public void FileList(String strFolderName)
    {
        String path = "data/data/com.example.maumcatcher/" + strFolderName;

        File directory = new File(path);
        File[] files = directory.listFiles();
        filesize =  files.length;

        List<Bitmap> myBitmap = new ArrayList<>();
        for (int i=0; i< files.length; i++) {
          //  myBitmap = BitmapFactory.decodeFile(directory.getAbsolutePath());
            filesNameList.add(path + "/" + files[i].getName());
        }

        File imgFile = new  File("/sdcard/Images/test_image.jpg");

        if(imgFile.exists()){




           // myImage.setImageBitmap(myBitmap);

        }

    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }

        // BaseAdapter를 상속받은 클래스가 구현해야 할 함수들은
        // { getCount(), getItem(), getItemId(), getView() }
        // Ctrl + i 를 눌러 한꺼번에 구현할 수 있습니다.
        @Override
        public int getCount() {
            return filesize;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(Integer.parseInt(filesNameList.get(i)));


            /*
            // 갤러리의 이미지뷰를 눌렀을 때
            // 다이얼로그뷰를 팝업하여 큰 이미지를 출력합니다.
            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPic = dialogView.findViewById(R.id.ivPic);
                    ivPic.setImageResource(picID[pos]);
                    dlg.setTitle("큰 이미지");
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });*/

            return imageView;
        }
    }

}