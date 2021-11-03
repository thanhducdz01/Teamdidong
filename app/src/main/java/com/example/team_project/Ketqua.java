package com.example.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Ketqua extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    ImageButton back_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        back_header = (ImageButton) findViewById(R.id.back_header);
        spinner1 = findViewById(R.id.spinner1);
        String [] value ={" Năm học 2020-2021"," Năm học 2019-2020"," Năm học 2018-2019"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.style_spiner,arrayList);
        spinner1.setAdapter(arrayAdapter);
        spinner2 = findViewById(R.id.spinner2);
        String [] value2 ={"Học Kì 191","Học kì 220","Học kì 320"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(value2));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.style_spiner2,arrayList2);
        spinner2.setAdapter(arrayAdapter2);
        ExpandableListView expandableListView =findViewById(R.id.expandableListView);
        HashMap<String, List<String>> item = new HashMap<>();
        ArrayList<String> ltjv = new ArrayList<>();
        ltjv.add("Chuyên cần: 9.00");
        ltjv.add("Giữa kỳ: 9.00");
        ltjv.add("Cuối kỳ: 9.00");
        ltjv.add("Tổng: 9.00");
        item.put("Lập trình java II",ltjv);
        ArrayList<String> tkcsdl = new ArrayList<>();
        tkcsdl.add("Chuyên cần: 9.00");
        tkcsdl.add("Giữa kỳ:9.00");
        tkcsdl.add("Cuối kỳ:9.00");
        tkcsdl.add("Tổng:9.00");
        item.put("Thiết kế cơ sở dữ liệu",tkcsdl);
        ArrayList<String> tacn = new ArrayList<>();
        tacn.add("Chuyên cần: 9.00");
        tacn.add("Giữa kỳ:9.00");
        tacn.add("Cuối kỳ:9.00");
        tacn.add("Tổng:9.00");
        item.put("Tiếng anh chuyên ngành",tacn);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(item);
        expandableListView.setAdapter(adapter);
        back_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ketqua.this, homepage_Activity.class);
                startActivity(intent);
            }
        });

    }
}