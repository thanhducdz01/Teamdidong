package com.example.team_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class student_information extends AppCompatActivity {


    DatePickerDialog datePickerDialog;
    Button dateButton;
    ImageButton homepage,btn_chatbox,btn_notification,btn_logOut;
    Dialog popupStudent_Dialog;
    TextView stuname,txt_msv,txt_khoa,txtlop,edtEmail,edtPhone,edtAddress,edtDate,edtGender;
    ImageButton btn_email,btn_phone,btn_add,btn_date,btn_gender;
    private static final String URLgetProfile= "http://192.168.0.103/UTEapp/getProfile.php";
    private static final String URLupdateProfile= "http://192.168.0.103/UTEapp/updateInfo.php";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
        btn_logOut = findViewById(R.id.btn_logOut);
        btn_email = findViewById(R.id.edit_email);
        btn_phone = findViewById(R.id.edit_phone);
        btn_add = findViewById(R.id.edit_addresss);
        btn_date = findViewById(R.id.edit_date);
        btn_gender = findViewById(R.id.edit_sex);
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
                finish();
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

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,"email");
            }
        });
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,"phone");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,"address");
            }
        });

        btn_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,"gender");
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,"date");
            }
        });
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Nam"));
        list.add(new Category("Nữ"));
        return list;
    }

    public void openDialog(int gravity,String nameupdate){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_studeninfo);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams winAttributes = window.getAttributes();
        window.setAttributes(winAttributes);
        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        Button btn_submitPopup, btn_closePopup;
        EditText editText_newInfo;
        TextView editInfo,gendervalue;
        Spinner spinGender;
        gendervalue = dialog.findViewById(R.id.gender_value);
        spinGender = dialog.findViewById(R.id.spin_gender);
        editInfo = dialog.findViewById(R.id.textview_editInfo);
        editText_newInfo = dialog.findViewById(R.id.edit_info);
        btn_submitPopup =  dialog.findViewById(R.id.btn_submitPopup);
        btn_closePopup = dialog.findViewById(R.id.btn_closePopup);
        if (nameupdate.equals("email")){
            editInfo.setText("Địa chỉ Email Mới");
            editText_newInfo.setVisibility(View.VISIBLE);
        }else if(nameupdate.equals("phone")){
            editText_newInfo.setVisibility(View.VISIBLE);
            editInfo.setText("Số điện thoại mới");
        }else if(nameupdate.equals("address")){
            editText_newInfo.setVisibility(View.VISIBLE);
            editInfo.setText("Địa chỉ mới");
        }else if(nameupdate.equals("gender")){
            CategoryAdapter categoryAdapter;
            categoryAdapter = new CategoryAdapter(student_information.this,R.layout.item_selected,getListCategory());
            spinGender.setAdapter(categoryAdapter);
            spinGender.setVisibility(View.VISIBLE);
            spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   gendervalue.setText(categoryAdapter.getItem(position).getGender());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            editInfo.setText("Chọn giới tính");
        }else if(nameupdate.equals("date")){
            initDatePicker();
            dateButton = dialog.findViewById(R.id.datePickerButton);
            dateButton.setVisibility(View.VISIBLE);
            dateButton.setText(getTodaysDate());
            editInfo.setText("Chọn ngày sinh");
        }

        btn_closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_submitPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameupdate.equals("date")){
                    updateProfile(nameupdate,dateButton.getText().toString());
                }else if (nameupdate.equals("gender")){
                    updateProfile(nameupdate,gendervalue.getText().toString());
                }else{
                    updateProfile(nameupdate,editText_newInfo.getText().toString());
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth,month,year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = makeDateString(dayOfMonth,month,year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,dayOfMonth);
    }

    public static boolean verifyEmail(String email) {
        if (email == null) return false;
        return email.matches(EMAIL_PATTERN);
    }
    public void updateProfile(String content,String value){
        value.trim();
         if(!verifyEmail(value) && content.equals("email")){
            Toast.makeText(getApplicationContext(), "EMAIL Không Hợp Lệ", Toast.LENGTH_SHORT).show();
        }else if (value.length() < 10 && content.equals("phone")) {
             Toast.makeText(getApplicationContext(), "SỐ ĐIỆN THOẠI chỉ nhận 10 hoặc 11 số", Toast.LENGTH_SHORT).show();
         }else if(!Pattern.matches("[0-9]+", value) && content.equals("phone")){
             Toast.makeText(getApplicationContext(), "SỐ ĐIỆN THOẠI chỉ nhận 'SỐ'", Toast.LENGTH_SHORT).show();
         } else{
            Intent intent = getIntent();
            String maSV = intent.getStringExtra("maSV");
            maSV.trim();
            StringRequest request = new StringRequest(Request.Method.POST, URLupdateProfile, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")){
                        Toast.makeText(getApplicationContext(),"Update Thành Công",Toast.LENGTH_SHORT).show();
                        AddProfile();
                    }else if(response.equals("exists")){
                        Toast.makeText(getApplicationContext(),"Đã tồn tại dữ liệu",Toast.LENGTH_SHORT).show();
                        AddProfile();
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
                    if (content.equals("email")){
                        pr.put("email",value);
                    }else if (content.equals("phone")){
                        pr.put("phone",value);
                    }else if (content.equals("address")){
                        pr.put("address",value);
                    }else if (content.equals("gender")){
                        pr.put("gender",value);
                    }else if (content.equals("date")){
                        pr.put("date",value);
                    }
                    return pr;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        }
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
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();

    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return year + "-" + month + "-" + dayOfMonth;
    }
}