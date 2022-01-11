package com.example.team_project.ChatModule;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.team_project.R;
import com.example.team_project.homepage_Activity;
import com.example.team_project.NotificationModule.notification_Activity;
import com.example.team_project.StudentProfileModule.student_information;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class chatbox_Activity extends AppCompatActivity {
    RecyclerView lvChat;
    private ArrayList<ChatMessage>  conversations ;
    private apdater_chatbox adapter;
    private FirebaseFirestore database;
    ImageButton homepage,btn_notification,btn_studentInfo;
    FloatingActionButton newChat;
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_notification = findViewById(R.id.img_button_notification);
        btn_studentInfo= findViewById(R.id.img_button_student);
        newChat =findViewById(R.id.newChat);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.chatbox);
        getView();
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String DocID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");
        System.out.println("DOC CU MEN NHANH DC: "+DocID);
        init();
        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatbox_Activity.this, userList.class);
                intent.putExtra("maSV",maSV);
                intent.putExtra("DocumentId",DocID);
                intent.putExtra("tenSV",tenSV);
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
                Intent intent = new Intent(chatbox_Activity.this, notification_Activity.class);
                startActivity(intent);
            }
        });

        btn_studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this, student_information.class);
                startActivity(intent);
            }
        });
    }

    private void  init(){
        conversations = new ArrayList<>();
        adapter = new apdater_chatbox(conversations);
        lvChat = findViewById(R.id.recListChat);
        lvChat.setAdapter(adapter);
        database = FirebaseFirestore.getInstance();

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private void AnhXa() {
//        lvStudent = findViewById(R.id.listviewStudent);
//        arrChatbox = new ArrayList<>();
//        arrChatbox.add(new chatbox_info("Nguyễn Vũ Dũng","Dũng đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Lê Thành Đức","Đức đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Nguyễn Trọng Khang","Khang đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Lê Lương Minh Hiếu","Hiếu đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Lương Văn Chương","Chương đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Hoàng Văn Minh","Minh đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Nguyễn Quyên Tâm","Tâm đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Hà Mỹ Long","Long đã gửi một tin nhắn",R.drawable.user));
//        arrChatbox.add(new chatbox_info("Nguyễn Đức An","An đã gửi một tin nhắn",R.drawable.user));
    }
}