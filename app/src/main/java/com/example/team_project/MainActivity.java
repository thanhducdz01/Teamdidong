package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        change = (LinearLayout) findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMicLogin();
            }
        });
    }
    public void openMicLogin(){
        Intent intent = new Intent(this,Mic_Login.class);
        startActivity(intent);
    }
}