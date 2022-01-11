package com.example.team_project.ChatModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.team_project.R;
import com.example.team_project.homepage_Activity;
import com.example.team_project.NotificationModule.notification_Activity;
import com.example.team_project.StudentProfileModule.student_information;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class chatbox_Activity extends AppCompatActivity implements  ConversionListener{
    RecyclerView lvChat;
    private ArrayList<ChatMessage> conversations;
    private apdater_chatbox adapter;
    private FirebaseFirestore database;
    ImageButton homepage, btn_notification, btn_studentInfo;
    FloatingActionButton newChat;

    void getView() {
        homepage = findViewById(R.id.img_button_homepage);
        btn_notification = findViewById(R.id.img_button_notification);
        btn_studentInfo = findViewById(R.id.img_button_student);
        newChat = findViewById(R.id.newChat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.chatbox);
        getView();
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String DocID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");
        System.out.println("DOC CU MEN NHANH DC: " + DocID);
        init();
        listenConversations();
        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatbox_Activity.this, userList.class);
                intent.putExtra("maSV", maSV);
                intent.putExtra("DocumentId", DocID);
                intent.putExtra("tenSV", tenSV);
                startActivity(intent);


            }
        });
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this, homepage_Activity.class);
                intent.putExtra("maSV", maSV);
                startActivity(intent);
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this, notification_Activity.class);
                startActivity(intent);
            }
        });

        btn_studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatbox_Activity.this, student_information.class);
                intent.putExtra("maSV", maSV);
                intent.putExtra("DocumentId", DocID);
                intent.putExtra("tenSV", tenSV);
                startActivity(intent);
            }
        });
    }

    private void init() {
        conversations = new ArrayList<>();
        adapter = new apdater_chatbox(conversations, this);
        lvChat = findViewById(R.id.recListChat);
        lvChat.setAdapter(adapter);
        database = FirebaseFirestore.getInstance();

    }

    private void  listenConversations(){
        Intent intentget = getIntent();
        String docID = intentget.getStringExtra("DocumentId");
        database.collection("Conversations")
                .whereEqualTo("senderID",docID)
                .addSnapshotListener(eventListener);
        database.collection("Conversations")
                .whereEqualTo("receiveID",docID)
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        Intent intentget = getIntent();
        String docID = intentget.getStringExtra("DocumentId");
        if (error != null) {
            return;
        }
        if (value != null) {
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    String senderID = documentChange.getDocument().getString("senderID");
                    String receiverID = documentChange.getDocument().getString("receiveID");

                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = senderID;
                    chatMessage.receiverID = receiverID;
                    if (docID.equals(senderID)) {
                        chatMessage.conversionName = documentChange.getDocument().getString("receiverName");
                        chatMessage.conversionId = documentChange.getDocument().getString("receiveID");
                    } else {
                        chatMessage.conversionName = documentChange.getDocument().getString("senderName");
                        chatMessage.conversionId = documentChange.getDocument().getString("senderID");

                    }
                    chatMessage.message = documentChange.getDocument().getString("lastMessage");
                    chatMessage.dateObject = documentChange.getDocument().getDate("Time");
                    conversations.add(chatMessage);

                } else if (documentChange.getType() == DocumentChange.Type.MODIFIED) {
                    for (int i = 0; i < conversations.size(); i++) {
                        String senderID = documentChange.getDocument().getString("senderID");
                        String receiverID = documentChange.getDocument().getString("receiveID");
                        if (conversations.get(i).senderId.equals(senderID) && conversations.get(i).receiverID.equals(receiverID)) {
                            conversations.get(i).message = documentChange.getDocument().getString("lastMessage");
                            conversations.get(i).dateObject = documentChange.getDocument().getDate("Time");
                            break;
                        }
                    }
                }

            }
            Collections.sort(conversations,(obj1, obj2) -> obj2.dateObject.compareTo(obj1.dateObject));
            adapter.notifyDataSetChanged();
            lvChat = findViewById(R.id.recListChat);
            lvChat.smoothScrollToPosition(0);
            lvChat.setVisibility(View.VISIBLE);


        }


    };

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), homepage_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onConversionClick(User user) {
        Intent intentget = getIntent();
        String maSV = intentget.getStringExtra("maSV");
        String DocID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");

        Intent intent = new Intent(getApplicationContext(),chatbox_details_Activity.class);
        intent.putExtra("maSV", maSV);
        intent.putExtra("DocumentId", DocID);
        intent.putExtra("tenSV", tenSV);
        intent.putExtra("Username",user);
        startActivity(intent);

    }
}