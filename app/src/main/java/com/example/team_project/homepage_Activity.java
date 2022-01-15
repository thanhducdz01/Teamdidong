package com.example.team_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
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
import com.example.team_project.ChatModule.chatbox_Activity;
import com.example.team_project.NotificationModule.notification_Activity;
import com.example.team_project.StudentProfileModule.student_information;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class homepage_Activity extends AppCompatActivity {
    ImageButton btn_info_student;
    ImageButton btn_notification;
    ImageButton btn_chatbox;
    LinearLayout btn_lhp;
    LinearLayout btn_tkb;
    LinearLayout btn_lichthi;
    LinearLayout btn_ketqua;
    TextView txtInfo;
    String maSV="1911505310109";
    static String DocumentID,tokenUser;
    static String studentName;
    private static final String URLgetProfile= "http://192.168.0.103/UTEapp/getProfile.php";

    void getView() {
        btn_info_student = findViewById(R.id.img_button_student);
        btn_chatbox = findViewById(R.id.img_button_chatbox);
        btn_notification= findViewById(R.id.img_button_notification);
        btn_lhp = findViewById(R.id.btn_lhp);
        btn_tkb =findViewById(R.id.btn_tkb);
        btn_lichthi = findViewById(R.id.btn_lichthi);
        btn_ketqua = findViewById(R.id.btn_ketqua);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_homepage);
        getView();
        AddSimpleProfile();
        btn_info_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, student_information.class);
                intent.putExtra("maSV",maSV);
                intent.putExtra("DocumentId",DocumentID);
                intent.putExtra("tenSV",studentName);
                System.out.println("ALEEEEEEEEEALEEEEEEEEE -- "+DocumentID);
                intent.putExtra("tokenUser",tokenUser);
                startActivity(intent);
            }
        });


        btn_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, chatbox_Activity.class);
                intent.putExtra("maSV",maSV);
                intent.putExtra("tenSV",studentName);
                intent.putExtra("DocumentId",DocumentID);
                System.out.println("DASKDJASLKDJALSKDJLKAS -- "+DocumentID);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, notification_Activity.class);
                startActivity(intent);
            }
        });
        btn_lhp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, LopHocPhan.class);
                startActivity(intent);
            }
        });
        btn_tkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, tkbieu_Activity.class);
                startActivity(intent);
            }
        });
        btn_lichthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, activity_Lichthi.class);
                startActivity(intent);
            }
        });
        btn_ketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage_Activity.this, activity_ketqua.class);
                startActivity(intent);
            }
        });
    }

    private void InsertInformationToFirebase(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        HashMap<String,Object> user = new HashMap<>();
        user.put("maSV",maSV);
        user.put("tenSV",studentName);
        firestore.collection("Users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    DocumentID = documentReference.getId();
                    getToken();
                })
                .addOnFailureListener(exception -> {

                });
    }
    private void alreadyExists(final String maSV) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference cref=firestore.collection("Users");
        Query q1=cref.whereEqualTo("maSV",maSV);
        q1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                boolean isExisting = false;
                String docID="null";
                for (DocumentSnapshot ds : queryDocumentSnapshots) {
                    String maSVcheck;
                    maSVcheck = ds.getString("maSV");
                    if (maSVcheck.equals(maSV)) {
                            isExisting = true;
                         docID = ds.getId();
                    }
                }
                if (!isExisting) {
                    InsertInformationToFirebase();
                }else{
                   DocumentID =docID;
                   getToken();
                }
            }
        });
    }

    private void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token){
        tokenUser = token;
        System.out.println("A________A_____: "+tokenUser);
        System.out.println("C________C_____: "+DocumentID);


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("Users").document(DocumentID);
        documentReference.update("token",token)
                .addOnSuccessListener(ocumentReference -> {
                })
                .addOnFailureListener(exception -> {

                });
    }





    private void AddSimpleProfile(){
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
                            String soDienThoai = object.getString("soDienThoai");
                            String diaChi = object.getString("diaChi");
                            String matKhauTK = object.getString("matKhauTK");
                            String matKhauEMAIL = object.getString("matKhauEMAIL");
                            txtInfo = findViewById(R.id.textview_information);
                            txtInfo.setText(tenSV+"\n"+maSV+"\n"+tenLop+"\n"+tenKhoa);
                            studentName = tenSV;
                            System.out.println("KAKAKAKKA -------"+studentName);
                            alreadyExists(maSV);
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
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    /**
     * Enables https connections
     */
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

}