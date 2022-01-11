package com.example.team_project.ChatModule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.R;

import java.util.List;

public class apdater_chatbox  extends  RecyclerView.Adapter<apdater_chatbox.ConversionVieHolder>{

        private final List<ChatMessage> chatMessages;
        private final ConversionListener conversionListener;

    public apdater_chatbox(List<ChatMessage> chatMessages, ConversionListener conversionListener) {
        this.chatMessages = chatMessages;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversionVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_chatbox, parent, false);
        return new ConversionVieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionVieHolder holder, int position) {
            holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversionVieHolder extends RecyclerView.ViewHolder{
            TextView txtTen,txtNoiDung;
            ImageView txtHinh;
            RelativeLayout MainlineChatbox;
            public ConversionVieHolder(@NonNull View itemView) {
                super(itemView);
                txtTen = itemView.findViewById(R.id.user_name);
                txtNoiDung = itemView.findViewById(R.id.text_noidung);
                txtHinh = itemView.findViewById(R.id.img_student);
                MainlineChatbox = itemView.findViewById(R.id.MainlineChatbox);
            }

            void setData(ChatMessage chatMessage){
                txtHinh.setImageResource(R.drawable.user);
                txtTen.setText(chatMessage.conversionName);
                txtNoiDung.setText(chatMessage.message);
                MainlineChatbox.setOnClickListener(v -> {
                    User user = new User();
                    user.id = chatMessage.conversionId;
                    user.name = chatMessage.conversionName;
                    conversionListener.onConversionClick(user);
                });
            }

        }


}
