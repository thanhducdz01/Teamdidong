package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class notification_Activity extends AppCompatActivity {
    ImageButton homepage,btn_chatbox,btn_studentInfo;
    ImageView viewDetailNoti;
    RelativeLayout btn_noti_details,btn_class_details,btn_pdt_details;
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_studentInfo= findViewById(R.id.img_button_student);
        viewDetailNoti = findViewById(R.id.detail_noti);
        btn_noti_details = findViewById(R.id.btn_noti_details);
        btn_class_details = findViewById(R.id.btn_class_details);
        btn_pdt_details= findViewById(R.id.btn_pdt_details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.notification);
        getView();

        btn_noti_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this,notification_details_Activity.class);
                startActivity(intent);
            }
        });

        btn_class_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this,notification_details_Activity.class);
                startActivity(intent);
            }
        });

        btn_pdt_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this,notification_details_Activity.class);
                startActivity(intent);
            }
        });

        viewDetailNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this,notification_details_Activity.class);
                startActivity(intent);
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this, homepage_Activity.class);
                startActivity(intent);
            }
        });



        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this,chatbox_Activity.class);
                startActivity(intent);
            }
        });

        btn_studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this, student_information.class);
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