package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_ketqua extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    ImageButton back_header;
    private ExpandableListView expandableListView;

    private List<GroupObject> groupObjects;
    private Map<GroupObject,List<ItemObject>> listMap;
    private kqExpandable adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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


        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listMap = getListMap();
        groupObjects = new ArrayList<>(listMap.keySet());
        adapter = new kqExpandable(groupObjects, listMap);
        expandableListView.setAdapter(adapter);

//        HashMap<String, List<String>> item = new HashMap<>();
//        ArrayList<String> ltjv = new ArrayList<>();
//        ltjv.add("Chuyên cần: 9.00");
//        ltjv.add("Giữa kỳ: 9.00");
//        ltjv.add("Cuối kỳ: 9.00");
//        ltjv.add("Tổng: 9.00");
//        item.put("Lập trình java II",ltjv);
//        ArrayList<String> tkcsdl = new ArrayList<>();
//        tkcsdl.add("Chuyên cần: 9.00");
//        tkcsdl.add("Giữa kỳ:9.00");
//        tkcsdl.add("Cuối kỳ:9.00");
//        tkcsdl.add("Tổng:9.00");
//        item.put("Thiết kế cơ sở dữ liệu",tkcsdl);
//        ArrayList<String> tacn = new ArrayList<>();
//        tacn.add("Chuyên cần: 9.00");
//        tacn.add("Giữa kỳ:9.00");
//        tacn.add("Cuối kỳ:9.00");
//        tacn.add("Tổng:9.00");
//        item.put("Tiếng anh chuyên ngành",tacn);
//        MyExpandableListAdapter adapter = new MyExpandableListAdapter(item);
//        expandableListView.setAdapter(adapter);
        back_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ketqua.this, homepage_Activity.class);
                startActivity(intent);
            }
        });

    }
    private Map<GroupObject,List<ItemObject>> getListMap(){
        Map<GroupObject, List<ItemObject>> objectListMap = new HashMap<>();

        GroupObject groupObject1 = new GroupObject(1,R.drawable.list_not,"Cơ sở dữ liệu II");
        GroupObject groupObject2 = new GroupObject(2,R.drawable.list_not,"Lập trình di động");
        GroupObject groupObject3 = new GroupObject(3,R.drawable.list,"Tiếng anh chuyên nghành");


        List<ItemObject> itemObjects1 = new ArrayList<>();
        itemObjects1.add(new ItemObject(1,"Chuyên cần: ","9.00"));
        itemObjects1.add(new ItemObject(2,"Giữa kỳ: ","9.00"));
        itemObjects1.add(new ItemObject(3,"Cuôi kỳ:","9.00"));
        itemObjects1.add(new ItemObject(4,"Tổng: ","9.00"));

        List<ItemObject> itemObjects2 = new ArrayList<>();
        itemObjects2.add(new ItemObject(5,"Chuyên cần: ","9.00"));
        itemObjects2.add(new ItemObject(6,"Giữa kỳ: ","9.00"));
        itemObjects2.add(new ItemObject(7,"Cuôi kỳ:","9.00"));
        itemObjects2.add(new ItemObject(8,"Tổng: ","9.00"));

        List<ItemObject> itemObjects3 = new ArrayList<>();
        itemObjects3.add(new ItemObject(9,"Chuyên cần: ","9.00"));
        itemObjects3.add(new ItemObject(10,"Giữa kỳ: ","9.00"));
        itemObjects3.add(new ItemObject(11,"Cuôi kỳ:","9.00"));
        itemObjects3.add(new ItemObject(12,"Tổng: ","9.00"));


        objectListMap.put(groupObject1,itemObjects1);
        objectListMap.put(groupObject2,itemObjects2);
        objectListMap.put(groupObject3,itemObjects3);
        return objectListMap;
    }
}
