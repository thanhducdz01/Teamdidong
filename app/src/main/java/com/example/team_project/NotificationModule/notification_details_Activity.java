package com.example.team_project.NotificationModule;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team_project.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class notification_details_Activity extends AppCompatActivity {


    ImageButton btn_Back;
    TextView title,content,date;
    notification notificationArrayList;
    void getView(){
        btn_Back =  findViewById(R.id.btn_back);
        title = findViewById(R.id.title);
        content = findViewById(R.id.noti_content);
        date = findViewById(R.id.noti_date);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.notification_details);
        getView();
        Intent intentget = getIntent();
        notificationArrayList = (notification) intentget.getSerializableExtra("NotiDetails");
        title.setText(notificationArrayList.getTieuDe());
        content.setText(notificationArrayList.getNoiDung());
        date.setText(notificationArrayList.getNgayGui());
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}