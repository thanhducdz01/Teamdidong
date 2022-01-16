package com.example.team_project.StudentProfileModule;

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
import com.example.team_project.ChatModule.chatbox_Activity;
import com.example.team_project.MainActivity;
import com.example.team_project.R;
import com.example.team_project.homepage_Activity;
import com.example.team_project.NotificationModule.notification_Activity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

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
    ImageButton homepage,btn_chatbox,btn_notification,btn_logOut,btn_changePassword;
    Dialog popupStudent_Dialog;
    TextView stuname,txt_msv,txt_khoa,txtlop,edtEmail,edtPhone,edtAddress,edtDate,edtGender;
    ImageButton btn_email,btn_phone,btn_add,btn_date,btn_gender;
    static String oldpas;
    private static final String URLgetProfile= "http://10.0.2.2:81/UTEapp/getProfile.php";
    private static final String URLupdateProfile= "http://10.0.2.2:81/UTEapp/updateInfo.php";
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
        btn_changePassword =findViewById(R.id.btn_changePassword);
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
        String tokenUser = intentget.getStringExtra("tokenUser");
        String docID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");



        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this, homepage_Activity.class);
                startActivity(intent);
                finish();
            }
        });


        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this, chatbox_Activity.class);
                intent.putExtra("maSV",maSV);
                intent.putExtra("tenSV",tenSV);
                intent.putExtra("DocumentId",docID);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_information.this, notification_Activity.class);
                intent.putExtra("maSV",maSV);
                intent.putExtra("tenSV",tenSV);
                intent.putExtra("DocumentId",docID);
                startActivity(intent);
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChangePassword(Gravity.CENTER);
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
    private  void  signOut(){
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String tokenUser = intentget.getStringExtra("tokenUser");
        String DocumentID = intentget.getStringExtra("DocumentId");
        System.out.println("TOKENNNNNNNNNNNN: "+tokenUser);
        System.out.println("DOCUMENTTTTTTT: "+DocumentID);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("Users").document(DocumentID);

        HashMap<String, Object> updates = new HashMap<>();
        updates.put("token", FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                });

    }
    private void openDialogChangePassword(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_changepassword);
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
        Button btn_submitChange, btn_closeChange;
        EditText editText_newPassword,editText_RenewPassword,editText_oldPassword;
        editText_newPassword = dialog.findViewById(R.id.edt_newpassword);
        editText_RenewPassword = dialog.findViewById(R.id.edt_renewpassword);
        editText_oldPassword = dialog.findViewById(R.id.edt_oldpassword);
        btn_submitChange =  dialog.findViewById(R.id.btn_submitChange);
        btn_closeChange = dialog.findViewById(R.id.btn_closeChange);


        btn_closeChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_submitChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText_oldPassword.getText().toString().equals(oldpas)){
                    Toast.makeText(getApplicationContext(), "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                }else{
                    String matKhau = editText_newPassword.getText().toString();
                    String reMatKhau = editText_RenewPassword.getText().toString();
                    matKhau.trim();
                    reMatKhau.trim();
                    System.out.println(matKhau+reMatKhau);
                    if(!matKhau.equals(reMatKhau) && !reMatKhau.equals("")){
                        Toast.makeText(getApplicationContext(), "Mật khẩu Nhập lại không khớp", Toast.LENGTH_SHORT).show();
                    }else if(!matKhau.matches("\\S+")){
                        Toast.makeText(getApplicationContext(), "Mật khẩu không được có Khoảng trắng", Toast.LENGTH_SHORT).show();
                    }else if(matKhau.equals("")){
                        Toast.makeText(getApplicationContext(), "Mật khẩu không được rỗng", Toast.LENGTH_SHORT).show();
                    }else{
                        updateProfile("changepas",matKhau);
                        Toast.makeText(getApplicationContext(), "ĐỔI MẬT KHẨU THÀNH CÔNG !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }



            }
        });
        dialog.show();
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
                        Toast.makeText(getApplicationContext(),"UPDATE THÀNH CÔNG",Toast.LENGTH_SHORT).show();
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
                    }else if (content.equals("changepas")){
                        System.out.println("LALALALAALAL ----- "+value);
                        pr.put("oldpas",value);
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
                            oldpas = matKhauTK;

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