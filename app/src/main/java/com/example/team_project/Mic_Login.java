package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Mic_Login extends AppCompatActivity {
    ImageButton imgBtn;
    Button btn_Miclogin;

    String Mail, MatKhau365;
    EditText txtMail, txtMatKhau365;
    String url = "http://10.0.2.2:81/Android_ute/login_acc_microsoft.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mic_login);

        Mail = MatKhau365 = "";
        txtMail = (EditText) findViewById(R.id.txtMail);
        txtMatKhau365 = (EditText) findViewById(R.id.txtMatKhau365);

        imgBtn = (ImageButton) findViewById(R.id.imgBtn);


        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backLogin();
            }
        });

        btn_Miclogin = findViewById(R.id.btn_micLogin);
        btn_Miclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_365();
            }
        });

    }
    public void backLogin(){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
    public void login_365(){
        Mail = txtMail.getText().toString().trim();
        MatKhau365 = txtMatKhau365.getText().toString().trim();
        if (!Mail.equals("") && !MatKhau365.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")){
                        Intent intent = new Intent(Mic_Login.this, homepage_Activity.class);
                        startActivity(intent);
                        finish();
                    }else if(response.equals("fail")){
                        Toast.makeText(Mic_Login.this, "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Mic_Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Email", Mail);
                    data.put("matKhauEMAIL", MatKhau365);
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