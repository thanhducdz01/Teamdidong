package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout change;
    TextView forgot;
    Button btn_login;

    String MaSv, MatKhau;
    EditText txtMaSV, txtMatKhau;
    String url = "http://10.0.2.2:81/Android_ute/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        MaSv = MatKhau = "";
        txtMaSV = (EditText) findViewById(R.id.txtMaSV);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        change = (LinearLayout) findViewById(R.id.change);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMicLogin();
            }
        });
        forgot =  findViewById(R.id.forgot_password);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Forgot.class);
                startActivity(intent);
            }
        });
    }
    public void openMicLogin(){
        Intent intent = new Intent(this,Mic_Login.class);
        startActivity(intent);
    }
    public void login(){
        MaSv = txtMaSV.getText().toString().trim();
        MatKhau = txtMatKhau.getText().toString().trim();
        if (!MaSv.equals("") && !MatKhau.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")){
                        Intent intent = new Intent(MainActivity.this, homepage_Activity.class);
                        intent.putExtra("maSV",MaSv);
                        startActivity(intent);
                        finish();
                    }else if(response.equals("fail")){
                        Toast.makeText(MainActivity.this, "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("maSV", MaSv);
                    data.put("matKhauTK", MatKhau);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }
    }
}