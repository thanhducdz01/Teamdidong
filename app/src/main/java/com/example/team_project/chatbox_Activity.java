package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class chatbox_Activity extends AppCompatActivity {
    ListView lvStudent;
    ArrayList<chatbox_info> arrChatbox;
    apdater_chatbox adapter;
    ImageButton homepage,btn_notification,btn_studentInfo;
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_notification = findViewById(R.id.img_button_notification);
        btn_studentInfo= findViewById(R.id.img_button_student);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.chatbox);
        AnhXa();
        adapter = new apdater_chatbox(this, R.layout.line_chatbox, arrChatbox);
        lvStudent.setAdapter(adapter);
        getView();

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(chatbox_Activity.this, chatbox_details_Activity.class);
                startActivity(intent);
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this, homepage_Activity.class);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this,notification_Activity.class);
                startActivity(intent);
            }
        });

        btn_studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this,student_information.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private void AnhXa() {
        lvStudent = findViewById(R.id.listviewStudent);
        arrChatbox = new ArrayList<>();
        arrChatbox.add(new chatbox_info("Nguyễn Vũ Dũng","Dũng đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Lê Thành Đức","Đức đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Nguyễn Trọng Khang","Khang đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Lê Lương Minh Hiếu","Hiếu đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Lương Văn Chương","Chương đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Hoàng Văn Minh","Minh đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Nguyễn Quyên Tâm","Tâm đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Hà Mỹ Long","Long đã gửi một tin nhắn",R.drawable.user));
        arrChatbox.add(new chatbox_info("Nguyễn Đức An","An đã gửi một tin nhắn",R.drawable.user));
    }
}