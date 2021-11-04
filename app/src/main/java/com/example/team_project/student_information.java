package com.example.team_project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class student_information extends AppCompatActivity {

    ImageButton homepage,btn_chatbox,btn_notification,btn_logOut;
    Dialog popupStudent_Dialog;
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
        btn_logOut = findViewById(R.id.btn_logOut);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,MainActivity.class);
                startActivity(intent);
            }
        });

        popupStudent_Dialog = new Dialog(this);

    }
    public void ShowPopup(View v){
        Button btn_submitPopup, btn_closePopup;
        EditText editText_newInfo;
        popupStudent_Dialog.setContentView(R.layout.popup_studeninfo);
        editText_newInfo = popupStudent_Dialog.findViewById(R.id.edit_info);
        btn_submitPopup =  popupStudent_Dialog.findViewById(R.id.btn_submitPopup);
        btn_closePopup = popupStudent_Dialog.findViewById(R.id.btn_closePopup);

        btn_closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupStudent_Dialog.dismiss();
            }
        });

        btn_submitPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(student_information.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                popupStudent_Dialog.dismiss();
            }
        });
        popupStudent_Dialog.show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}