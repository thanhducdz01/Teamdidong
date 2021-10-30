package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Mic_Login extends AppCompatActivity {
    ImageButton imgBtn;
    TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mic_login);

        imgBtn = (ImageButton) findViewById(R.id.imgBtn);
        forgot = (TextView) findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotActivity();
            }
        });

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
    public void forgotActivity(){
        Intent back = new Intent(this, Forgot.class);
        startActivity(back);
    }
}