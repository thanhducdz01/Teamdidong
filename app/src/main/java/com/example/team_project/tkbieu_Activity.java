package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class tkbieu_Activity extends AppCompatActivity {
    RelativeLayout btn_t2,btn_t3,btn_t4;
    String urlGetData ="http://192.168.145.1/Didong/ketnoi.php";
    ListView LvTKB;
    ArrayList<tkb> arrayTKB;
    TKBAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_tkb);
        LvTKB =(ListView) findViewById(R.id.listviewTKB);
        arrayTKB = new ArrayList<>();
        adapter = new TKBAdapter(this,R.layout.fragment_tkb_t2, arrayTKB);
        LvTKB.setAdapter(adapter);

        GetData(urlGetData);

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
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(tkbieu_Activity.this, response.toString(),Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tkbieu_Activity.this, "Loi! ",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}