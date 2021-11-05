package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class tkbieu_Activity extends AppCompatActivity {
    RelativeLayout btn_t2,btn_t3,btn_t4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_tkb);
        ImageView imageView = (ImageView) findViewById(R.id.back_header);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tkbieu_Activity.this, homepage_Activity.class);
                startActivity(intent);
            }
        });

        btn_t2 = findViewById(R.id.btn_t2);
        btn_t3 = findViewById(R.id.btn_t3);
        btn_t4 = findViewById(R.id.btn_t4);

        btn_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new tkb_t2());
                btn_t2.setBackgroundColor(1);
                btn_t3.setBackgroundResource(R.drawable.boderday);
                btn_t4.setBackgroundResource(R.drawable.boderday);
            }
        });

        btn_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new tkb_t3());
                btn_t3.setBackgroundColor(1);
                btn_t2.setBackgroundResource(R.drawable.boderday);
                btn_t4.setBackgroundResource(R.drawable.boderday);
            }
        });

        btn_t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new tkb_t4());
                btn_t4.setBackgroundColor(1);
                btn_t2.setBackgroundResource(R.drawable.boderday);
                btn_t3.setBackgroundResource(R.drawable.boderday);
            }
        });
    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
    }

}