package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class chatbox_details_Activity extends AppCompatActivity {
    ImageButton btn_Chatbox_back,btn_send;
    MessageApdapter adapter;
    RecyclerView message_content;
    List<message_model> listText;
    EditText textsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.chatbox_details);
        btn_Chatbox_back = findViewById(R.id.btn_back_chatbox);
        btn_Chatbox_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_details_Activity.this,chatbox_Activity.class);
                startActivity(intent);
            }
        });

        message_content = findViewById(R.id.message_content);
        message_content.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        message_content.setLayoutManager(linearLayoutManager);
        textsend = findViewById(R.id.edittext_send);
        btn_send = findViewById(R.id.btn_send);
        listText = new ArrayList<>();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnhXa();
                String msg = textsend.getText().toString();
                if (!msg.equals("")){
                    adapter = new MessageApdapter(chatbox_details_Activity.this,listText);
                    message_content.setAdapter(adapter);
                }
                textsend.setText("");
            }
        });

    }
    private void  AnhXa(){
        String msg = textsend.getText().toString();
        listText.add(new message_model("Nguyễn Vũ Dũng","Lê Thành Đức",msg));
    }

}