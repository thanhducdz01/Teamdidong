package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Forgot extends AppCompatActivity {
    ImageButton back;
    Button btnContinue;

    String forgot;
    EditText txtForgot;
    String url = "http://192.168.0.103/Android_ute/forgot.php";

    loadingdialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);
        loading = new loadingdialog(Forgot.this);

        back = (ImageButton) findViewById(R.id.btnBack1);
        btnContinue = (Button) findViewById(R.id.conti1);

        forgot = "";
        txtForgot = (EditText) findViewById(R.id.txtforgot);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backMicLogin();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPass();
            }
        });
    }

    public void backMicLogin(){
        Intent intent = new Intent(this, Mic_Login.class);
        startActivity(intent);
    }
    public void forgotPass(){
        forgot = txtForgot.getText().toString().trim();
        if (!forgot.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")){
                        Toast.makeText(Forgot.this, "Đã gửi mật khẩu qua mail", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Forgot.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(response.equals("fail")){
                        Toast.makeText(Forgot.this, "Sai mail sinh viên", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Forgot.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Email", forgot);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }
        loading.startloading();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.dismissDialog();
            }
        }, 5500);

    }


}