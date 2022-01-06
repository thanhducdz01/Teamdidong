package com.example.team_project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class student_information extends AppCompatActivity {

    ImageButton homepage,btn_chatbox,btn_notification,btn_logOut;
    Dialog popupStudent_Dialog;
    TextView stuname,txt_msv,txt_khoa,txtlop,edtEmail,edtPhone,edtAddress,edtDate,edtGender;
    private static final String URLgetProfile= "http://192.168.0.103/UTEapp/getProfile.php";
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
        btn_logOut = findViewById(R.id.btn_logOut);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.infomation_student);
        getView();
        AddProfile();
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,homepage_Activity.class);
                startActivity(intent);
            }
        });

        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,chatbox_Activity.class);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,notification_Activity.class);
                startActivity(intent);
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this,MainActivity.class);
                startActivity(intent);
            }
        });

        popupStudent_Dialog = new Dialog(this);

    }
    private void AddProfile(){
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        StringRequest request = new StringRequest(Request.Method.POST, URLgetProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    System.out.println(result);
                    if (result.equals("success")){
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String maSV = object.getString("maSV");
                            String tenSV = object.getString("tenSinhVien");
                            String tenLop = object.getString("tenLop");
                            String tenNganh = object.getString("tenNganh");
                            String tenKhoa = object.getString("tenKhoa");
                            String gioiTinh = object.getString("gioiTinh");
                            String email = object.getString("email");
                            String ngaySinh = object.getString("ngaySinh");
                            String soDienThoai = object.getString("soDienThoai");
                            String diaChi = object.getString("diaChi");
                            String matKhauTK = object.getString("matKhauTK");
                            String matKhauEMAIL = object.getString("matKhauEMAIL");
                            stuname = findViewById(R.id.text_stuname);
                            txt_msv = findViewById(R.id.text_msv);
                            txt_khoa = findViewById(R.id.text_khoa);
                            txtlop = findViewById(R.id.text_class);
                            edtEmail = findViewById(R.id.text_cemail);
                            edtPhone = findViewById(R.id.text_cphone);
                            edtAddress = findViewById(R.id.text_caddress);
                            edtDate = findViewById(R.id.text_cdate);
                            edtGender = findViewById(R.id.text_cgender);

                            stuname.setText("Xin chào, "+tenSV);
                            txt_msv.setText("Mã sinh viên: "+maSV);
                            txt_khoa.setText(tenKhoa);
                            txtlop.setText("Lớp: "+tenLop);
                            edtEmail.setText(email);
                            edtPhone.setText(soDienThoai);
                            edtAddress.setText(diaChi);
                            edtDate.setText(ngaySinh);
                            edtGender.setText(gioiTinh);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pr = new HashMap<String,String>();
                pr.put("maSV",maSV);
                return pr;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
    public void ShowPopup(View v){
        Button btn_submitPopup, btn_closePopup;
        EditText editText_newInfo;
        popupStudent_Dialog.setContentView(R.layout.popup_studeninfo);
        editText_newInfo = popupStudent_Dialog.findViewById(R.id.edit_info);
        btn_submitPopup =  popupStudent_Dialog.findViewById(R.id.btn_submitPopup);
        btn_closePopup = popupStudent_Dialog.findViewById(R.id.btn_closePopup);

        btn_closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupStudent_Dialog.dismiss();
            }
        });

        btn_submitPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(student_information.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                popupStudent_Dialog.dismiss();
            }
        });
        popupStudent_Dialog.show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}