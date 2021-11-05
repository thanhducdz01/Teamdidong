package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class homepage_Activity extends AppCompatActivity {
    ImageButton btn_info_student;
    ImageButton btn_notification;
    ImageButton btn_chatbox;
    LinearLayout btn_lhp;
    LinearLayout btn_tkb;
    LinearLayout btn_lichthi;
    LinearLayout btn_ketqua;

    void getView() {
        btn_info_student = findViewById(R.id.img_button_student);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
        btn_lhp = findViewById(R.id.btn_lhp);
        btn_tkb =findViewById(R.id.btn_tkb);
        btn_lichthi = findViewById(R.id.btn_lichthi);
        btn_ketqua = findViewById(R.id.btn_ketqua);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_homepage);
        getView();
        btn_info_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, student_information.class);
                startActivity(intent);
            }
        });

        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this,chatbox_Activity.class);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this,notification_Activity.class);
                startActivity(intent);
            }
        });
        btn_lhp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, LopHocPhan.class);
                startActivity(intent);
            }
        });
        btn_tkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, tkbieu_Activity.class);
                startActivity(intent);
            }
        });
        btn_lichthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, activity_Lichthi.class);
                startActivity(intent);
            }
        });
        btn_ketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, activity_ketqua.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

}