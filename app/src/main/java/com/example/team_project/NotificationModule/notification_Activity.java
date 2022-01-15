package com.example.team_project.NotificationModule;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.team_project.ChatModule.chatbox_Activity;
import com.example.team_project.R;
import com.example.team_project.StudentProfileModule.student_information;
import com.example.team_project.homepage_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class notification_Activity extends AppCompatActivity{
    ImageButton homepage,btn_chatbox,btn_studentInfo;
    RelativeLayout btn_noti_details,btn_class_details,btn_pdt_details;
    ArrayList<notification> notificationArrayList;
    notificationAdapter notificationAdapter;
    RecyclerView recNotiview;
    String url ="http://192.168.0.103/UTEapp/getNotification.php";
    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_studentInfo= findViewById(R.id.img_button_student);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.notification);
        getView();
        notificationArrayList = new ArrayList<>();
        recNotiview = findViewById(R.id.recViewNoti);
        notificationAdapter = new notificationAdapter(notificationArrayList,this);
        recNotiview.setAdapter(notificationAdapter);
      getListNoti(url);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this, homepage_Activity.class);
                startActivity(intent);
            }
        });



        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this, chatbox_Activity.class);
                startActivity(intent);
            }
        });

        btn_studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_Activity.this, student_information.class);
                startActivity(intent);
            }
        });
    }
    private void getListNoti(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                notificationArrayList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        notificationArrayList.add(new notification(
                                object.getString("Id"),
                                object.getString("NguoiGui"),
                                object.getString("TieuDe"),
                                object.getString("NoiDung"),
                                object.getString("NgayGui")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                notificationAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(notification_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}