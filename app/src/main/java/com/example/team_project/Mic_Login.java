package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Mic_Login extends AppCompatActivity {
    ImageButton imgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mic_login);

        imgBtn = (ImageButton) findViewById(R.id.imgBtn);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backLogin();
            }
        });
    }
    public void backLogin(){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}