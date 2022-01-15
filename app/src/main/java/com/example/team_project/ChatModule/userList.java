package com.example.team_project.ChatModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.team_project.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class userList extends AppCompatActivity  implements  UserListener{

    RecyclerView recyclerView;
    ImageButton back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_user_list);
        getUsers();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void  getUsers(){
        recyclerView = findViewById(R.id.userRecycler);
        back = findViewById(R.id.btn_backChatbox);
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String docID = intentget.getStringExtra("DocumentId");
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Users")
                .get()
                .addOnCompleteListener(task -> {
                    String currentID = docID;
                    if (task.isSuccessful() && task.getResult() !=null){
                        List<User> users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            if (currentID.equals(queryDocumentSnapshot.getId())){
                                continue;
                            }
                            User user = new User();
                            user.image = R.drawable.user;
                            user.name = queryDocumentSnapshot.getString("tenSV");
                            user.token = queryDocumentSnapshot.getString("token");
                            user.id = queryDocumentSnapshot.getId();
                            users.add(user);
                        }
                        if (users.size()>0) {
                            UserAdapter userAdapter = new UserAdapter(users, this,this);
                            recyclerView.setAdapter(userAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(getApplicationContext(),chatbox_details_Activity.class);
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String docID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");
        intent.putExtra("maSV",maSV);
        intent.putExtra("DocumentId",docID);
        intent.putExtra("tenSV",tenSV);
        System.out.println("ALEEEEEEEEEALEEEEEEEEE -- "+docID);
        intent.putExtra("Username",user);
        startActivity(intent);
        finish();
    }
}