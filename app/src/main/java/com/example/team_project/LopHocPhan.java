package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LopHocPhan extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private List<GroupObject> groupObjects;
    private Map<GroupObject,List<ItemObject>> listMap;
    private ExpandableAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc_phan);
        getSupportActionBar().hide();

        expandableListView = (ExpandableListView) findViewById(R.id.expand_listview);
        listMap = getListMap();
        groupObjects = new ArrayList<>(listMap.keySet());
        adapter = new ExpandableAdapter(groupObjects, listMap);
        expandableListView.setAdapter(adapter);
        ImageView imageView = (ImageView) findViewById(R.id.back_header);
        imageView.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                Intent intent = new Intent(LopHocPhan.this, homepage_Activity.class);
                startActivity(intent);
            }
        });




    }

    private Map<GroupObject,List<ItemObject>> getListMap(){
        Map<GroupObject, List<ItemObject>> objectListMap = new HashMap<>();

        GroupObject groupObject1 = new GroupObject(1,R.drawable.book_open,"Chương Trình Đào Tạo");
        GroupObject groupObject2 = new GroupObject(2,R.drawable.list_not,"Lớp Học Phần Chưa Đăng Ký");
        GroupObject groupObject3 = new GroupObject(3,R.drawable.list,"Lớp Học Phần Đã Đăng Ký");


        List<ItemObject> itemObjects1 = new ArrayList<>();
        itemObjects1.add(new ItemObject(1,"Chường trình cử nhân","Xem chi tiêt"));
        itemObjects1.add(new ItemObject(2,"Chường trình kỹ sư","Xem chi tiêt"));

        List<ItemObject> itemObjects2 = new ArrayList<>();
        itemObjects2.add(new ItemObject(3,"Học phần đại cương","Xem chi tiêt"));
        itemObjects2.add(new ItemObject(4,"Học phần kỹ năng mềm","Xem chi tiêt"));
        itemObjects2.add(new ItemObject(5,"Học phần bắt buộc","Xem chi tiêt"));
        itemObjects2.add(new ItemObject(6,"Học phần tự chọn bắc buộc","Xem chi tiêt"));
        itemObjects2.add(new ItemObject(7,"Học phần giáo dục thể chất","Xem chi tiêt"));
        itemObjects2.add(new ItemObject(8,"Học phần ngoại ngữ","Xem chi tiêt"));

        List<ItemObject> itemObjects3 = new ArrayList<>();
        itemObjects3.add(new ItemObject(9,"Học phần đại cương","Xem chi tiêt"));
        itemObjects3.add(new ItemObject(10,"Học phần kỹ năng mềm","Xem chi tiêt"));
        itemObjects3.add(new ItemObject(11,"Học phần bắt buộc","Xem chi tiêt"));
        itemObjects3.add(new ItemObject(12,"Học phần tự chọn bắc buộc","Xem chi tiêt"));
        itemObjects3.add(new ItemObject(13,"Học phần giáo dục thể chất","Xem chi tiêt"));
        itemObjects3.add(new ItemObject(14,"Học phần ngoại ngữ","Xem chi tiêt"));


        objectListMap.put(groupObject1,itemObjects1);
        objectListMap.put(groupObject2,itemObjects2);
        objectListMap.put(groupObject3,itemObjects3);
        return objectListMap;
    }
}