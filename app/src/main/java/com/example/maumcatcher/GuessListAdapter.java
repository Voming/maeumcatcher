package com.example.maumcatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.maumcatcher.GuessQuestion;

public class GuessListAdapter extends ArrayAdapter<GuessQuestion> {

    private ArrayList<GuessQuestion> objects;

    public GuessListAdapter(Context context, int textViewResourceId, ArrayList<GuessQuestion> qUESTIONS)
    {
        super(context, textViewResourceId, qUESTIONS);
        objects = qUESTIONS;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.guessname_list, null);

        TextView txtqstn = (TextView) v.findViewById(R.id.txtAnswerList);
        TextView txtselectedANS = (TextView) v.findViewById(R.id.txtSelectedAnswers);
        TextView txtactualANS = (TextView) v.findViewById(R.id.txtActualAnswers);

        txtqstn.setText("문제: " + objects.get(position).getQuestion());
        txtselectedANS.setText("선택한 답: " + objects.get(position).getChoice());
        txtactualANS.setText("정답: " + objects.get(position).getAnswer());

        return v;
    }

}
