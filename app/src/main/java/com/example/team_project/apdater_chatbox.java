package com.example.team_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class apdater_chatbox extends BaseAdapter {
    private Context context;
    private int layout;
    private List<chatbox_info> chatbox_infoList;

    public apdater_chatbox(Context context, int layout, List<chatbox_info> chatbox) {
        this.context = context;
        this.layout = layout;
        this.chatbox_infoList = chatbox;
    }

    @Override
    public int getCount() {
        return chatbox_infoList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        TextView textTen = view.findViewById(R.id.user_name);
        TextView textNoiDung = view.findViewById(R.id.text_noidung);
        ImageView textHinh = view.findViewById(R.id.img_student);

        chatbox_info chatbox_info = chatbox_infoList.get(i);
        textTen.setText(chatbox_info.getTen());
        textNoiDung.setText(chatbox_info.getNoidung());
        textHinh.setImageResource(chatbox_info.getHinh());

        return view;
    }
}
