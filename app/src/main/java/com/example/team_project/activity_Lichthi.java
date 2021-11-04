package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_Lichthi extends AppCompatActivity {

    Spinner spinner1;
    ImageButton back_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichthi);
        spinner1 = findViewById(R.id.spinner1);
        back_header = (ImageButton) findViewById(R.id.back_header);
        String [] value ={"Năm học 2020-2021","Năm học 2019-2020","Năm học 2018-2019"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.style_spiner,arrayList);
        spinner1.setAdapter(arrayAdapter);
        back_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_Lichthi.this, homepage_Activity.class);
                startActivity(intent);
            }
        });
    }
}