package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class student_information extends AppCompatActivity {

    ImageButton homepage,btn_chatbox,btn_notification;

    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.infomation_student);
        getView();
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,homepage_Activity.class);
                startActivity(intent);
            }
        });

        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,chatbox_Activity.class);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,notification_Activity.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}