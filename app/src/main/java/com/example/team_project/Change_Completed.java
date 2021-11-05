package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Change_Completed extends AppCompatActivity {
    Button btnComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_completed);

        btnComplete = (Button) findViewById(R.id.btnComplete);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    backMicLogin();
            }
        });
    }
    public void backMicLogin(){
        Intent intent = new Intent(this, Mic_Login.class);
        startActivity(intent);
    }
}