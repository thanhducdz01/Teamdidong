package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Forgot extends AppCompatActivity {
    ImageButton back;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);

        back = (ImageButton) findViewById(R.id.btnBack1);
        btnContinue = (Button) findViewById(R.id.conti1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backMicLogin();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPasswordStart();
            }
        });
    }

    public void backMicLogin(){
        Intent intent = new Intent(this, Mic_Login.class);
        startActivity(intent);
    }

    public void  newPasswordStart(){
        Intent intent = new Intent(this, New_Password.class);
        startActivity(intent);
    }
}