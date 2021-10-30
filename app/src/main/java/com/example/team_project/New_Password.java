package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class New_Password extends AppCompatActivity {
    ImageButton btnBack;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_password);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnContinue = (Button) findViewById(R.id.btnContinue);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backForgot();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComleteStart();
            }
        });
    }

    public void backForgot(){
        Intent intent = new Intent(this, Forgot.class);
        startActivity(intent);
    }

    public void ComleteStart(){
        Intent intent = new Intent(this, Change_Completed.class);
        startActivity(intent);
    }
}