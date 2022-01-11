package com.example.team_project.ChatModule;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class chatbox_details_Activity extends AppCompatActivity {
    ImageButton btn_Chatbox_back,btn_send;
    MessageApdapter adapter;
    RecyclerView message_content;
    List<message_model> listText;
    List<ChatMessage> chatMessages;
    private MessageApdapter messageApdapter;
    private FirebaseFirestore database;
    private String conversionId = null;
    EditText textsend;
    User receiveUser;
    TextView txtStudenname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.chatbox_details);
        loadReceiverUser();
        init();
        listenMessages();
        btn_Chatbox_back = findViewById(R.id.btn_back_chatbox);
        btn_Chatbox_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        message_content = findViewById(R.id.message_content);
//        message_content.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setStackFromEnd(true);
//        message_content.setLayoutManager(linearLayoutManager);
        textsend = findViewById(R.id.edittext_send);
        btn_send = findViewById(R.id.btn_send);
        listText = new ArrayList<>();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = textsend.getText().toString();
                if (!msg.equals("")){
                        sendMessage();
                }
            }
        });

    }

    private void   listenMessages(){
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        String docID = intentget.getStringExtra("DocumentId");
        database.collection("Chat")
                .whereEqualTo("senderID",docID)
                .whereEqualTo("receiveID",receiveUser.id)
                .addSnapshotListener(eventListener);
        database.collection("Chat")
                .whereEqualTo("senderID",receiveUser.id)
                .whereEqualTo("receiveID",docID)
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) ->{
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        String docID = intentget.getStringExtra("DocumentId");
        if(error != null){
            return;
        }
        if (value !=null){
            int count = chatMessages.size();
            for (DocumentChange documentChange :value.getDocumentChanges()){
                if (documentChange.getType() == DocumentChange.Type.ADDED){
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = documentChange.getDocument().getString("senderID");
                    chatMessage.receiverID = documentChange.getDocument().getString("receiveID");
                    chatMessage.message = documentChange.getDocument().getString("Message");
                    chatMessage.dateTime = getReaderableTime(documentChange.getDocument().getDate("Time"));
                    chatMessage.dateObject =documentChange.getDocument().getDate("Time");
                    System.out.println("HIEN RA NEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    chatMessages.add(chatMessage);
                }
            }

            Collections.sort(chatMessages,(obj1, obj2) -> obj1.dateObject.compareTo(obj2.dateObject));
            if (count == 0){
                messageApdapter.notifyDataSetChanged();
            }else{
                messageApdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
            }
            message_content = findViewById(R.id.message_content);
            message_content.setVisibility(View.VISIBLE);
        }
        if (conversionId == null){
            checkForConversion();
        }

    };

    private void   init(){
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        String maSV = intentget.getStringExtra("maSV");
        String docID = intentget.getStringExtra("DocumentId");
        chatMessages = new ArrayList<>();
        messageApdapter = new MessageApdapter(
                chatMessages,docID
        );
        message_content = findViewById(R.id.message_content);
        message_content.setAdapter(messageApdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void sendMessage(){
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        String docID = intentget.getStringExtra("DocumentId");
        String tenSV = intentget.getStringExtra("tenSV");
        String msg = textsend.getText().toString();
        HashMap<String, Object> message = new HashMap<>();
        message.put("senderID",docID);
        message.put("receiveID",receiveUser.id);
        message.put("Message",msg);
        message.put("Time",new Date());
        database.collection("Chat").add(message);
        if (conversionId != null){
            updateConversion(msg);
        }else{
            HashMap<String, Object> conversion = new HashMap<>();
            conversion.put("senderID",docID);
            conversion.put("senderName",tenSV);
            conversion.put("receiveID",receiveUser.id);
            conversion.put("receiverName",receiveUser.name);
            conversion.put("lastMessage",msg);
            conversion.put("Time",new Date());
            addConversion(conversion);
        }
        textsend.setText(null);
    }

    private  void loadReceiverUser(){
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        txtStudenname = findViewById(R.id.textview_nameStudent);
        txtStudenname.setText(receiveUser.name);
    }

    private String getReaderableTime(Date date){
        return new SimpleDateFormat("dd-MMMM-yyyy, hh:mm ", Locale.getDefault()).format(date);
    }

    private void addConversion(HashMap<String, Object> conversion){
        database.collection("Conversations")
                .add(conversion)
                .addOnSuccessListener(documentReference -> conversionId = documentReference.getId());
    }

    private void updateConversion(String message){
        DocumentReference documentReference = database.collection("Conversations").document(conversionId);
        documentReference.update(
                "lastMessage",message,
                "Time",new Date()

        );
    }

    private void checkForConversion(){
        Intent intentget = getIntent();
        receiveUser = (User) intentget.getSerializableExtra("Username");
        String docID = intentget.getStringExtra("DocumentId");
        if (chatMessages.size() != 0){
            checkForConversionRemote(docID,receiveUser.id);
            checkForConversionRemote(receiveUser.id,docID);
        }
    }

    private void checkForConversionRemote(String senderID, String receiveID){
        database.collection("Conversations")
                .whereEqualTo("senderID",senderID)
                .whereEqualTo("receiveID",receiveID)
                .get()
                .addOnCompleteListener(conversionOncompelete);
    }

    private final OnCompleteListener <QuerySnapshot> conversionOncompelete = task -> {
        if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
            conversionId = documentSnapshot.getId();
        }
    };

}