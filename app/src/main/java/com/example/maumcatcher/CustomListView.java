package com.example.maumcatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomListView extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    private ArrayList<ListData> listViewData = null;
    private int count = 0;

    public CustomListView(ArrayList<ListData> listData) {
        listViewData = listData;
        count = listViewData.size();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final Context context = parent.getContext();
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = layoutInflater.inflate(R.layout.custom_listview, parent, false);
        }

        TextView class_id = convertView.findViewById(R.id.class_id);
        TextView class_txt = convertView.findViewById(R.id.class_txt);
        TextView name = convertView.findViewById(R.id.name);
        TextView body1 = convertView.findViewById(R.id.body1);
        TextView body2 = convertView.findViewById(R.id.body2);
        TextView body3 = convertView.findViewById(R.id.body3);

        class_id.setText(listViewData.get(position).class_id);
        class_txt.setText(listViewData.get(position).class_txt);
        name.setText(listViewData.get(position).name);
        body1.setText(listViewData.get(position).body1);
        body2.setText(listViewData.get(position).body2);
        body3.setText(listViewData.get(position).body3);

        return convertView;
    }

    public class ListData {
        public String class_id = "";
        public String class_txt = "";
        public String name = "";
        public String body1 = "";
        public String body2 = "";
        public String body3 = "";
    }

}
