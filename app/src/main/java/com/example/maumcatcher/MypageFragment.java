package com.example.maumcatcher;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



public class MypageFragment extends Fragment {
    String id, pw, name, age;




    public MypageFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getArguments();

        if(extra != null){
            id = extra.getString("id");
            pw = extra.getString("pw");
            name = extra.getString("name");
            age = extra.getString("age");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        Button button2 = (Button) view.findViewById(R.id.button2); //fragment에서 findViewByid는 view.을 이용해서 사용
        Button rewrite = (Button) view.findViewById(R.id.rewrite);
        TextView game1 = view.findViewById(R.id.game1);
        TextView game2 = view.findViewById(R.id.game2);
        TextView game3 = view.findViewById(R.id.game3);

        TextView nametxt = view.findViewById(R.id.name);
        TextView emailtxt = view.findViewById(R.id.email);

        nametxt.setText(name);
        emailtxt.setText(id);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TutorialActivity.class); //fragment라서 activity intent와는 다른 방식
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RewriteLoginActivity.class); //fragment라서 activity intent와는 다른 방식
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("id", id);
                intent.putExtra("pw", pw);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                startActivity(intent);
            }
        });
        return view;

    }


}