package com.example.maumcatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmotionSubFragment extends Fragment {
    private View view;
    private ImageButton btn1;
    private ImageButton btn2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_emotion, container, false);

        view = inflater.inflate(R.layout.fragment_emotion_sub,container,false);

        btn1 = (ImageButton) view.findViewById(R.id.imageButton1); //fragment에서 findViewByid는 view.을 이용해서 사용
        btn2 = (ImageButton) view.findViewById(R.id.imageButton2); //fragment에서 findViewByid는 view.을 이용해서 사용

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),FollowFacesActivity.class); //fragment라서 activity intent와는 다른 방식
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EmotionSubFragment.class); //fragment라서 activity intent와는 다른 방식
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return view;
    }
}