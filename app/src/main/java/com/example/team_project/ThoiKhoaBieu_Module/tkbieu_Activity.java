package com.example.team_project.ThoiKhoaBieu_Module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.team_project.R;

import java.util.ArrayList;

public class tkbieu_Activity extends AppCompatActivity {
    RelativeLayout btn_t2,btn_t3,btn_t4,btn_t5,btn_t6,btn_t7;
    private ImageButton btn_back;
    String urlGetData ="http://10.0.2.2:81/Didong/ketnoi.php";
    RecyclerView LvTKB;
    ArrayList<tkb> arrayTKB;
    TKBAdapter adapter;
    static String masv="1911505310115";

    private void getView(){
        btn_back = findViewById(R.id.back_header);
        btn_t2 = findViewById(R.id.btn_t2);
        btn_t3 = findViewById(R.id.btn_t3);
        btn_t4 = findViewById(R.id.btn_t4);
        btn_t5 = findViewById(R.id.btn_t5);
        btn_t6 = findViewById(R.id.btn_t6);
        btn_t7 = findViewById(R.id.btn_t7);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_tkb);
        getView();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t2());
            }
        });

        btn_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t3());
            }
        });

        btn_t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t3());
            }
        });

        btn_t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t5());
            }
        });

        btn_t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t6());
            }
        });

        btn_t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new tkb_t7());
            }
        });

    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        Bundle bundle = new Bundle();
        bundle.putString("maSV",masv);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
    }


}