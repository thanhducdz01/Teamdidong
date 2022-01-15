package com.example.team_project.ChatModule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.R;

import java.util.List;

public class MessageApdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  final  List<ChatMessage> chatMessages;
    private  final  String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;
    public MessageApdapter(List<ChatMessage> chatMessages, String senderId) {
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return  new SentMessageViewHolder(v);
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_receive, parent, false);
            return new ReceiveMessageViewHolder(
                    v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == VIEW_TYPE_SENT){
                ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
            }else{
                ((ReceiveMessageViewHolder) holder).setData(chatMessages.get(position));
            }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }else{
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        public TextView send_message,timeSent;
        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            send_message = itemView.findViewById(R.id.show_message);
            timeSent = itemView.findViewById(R.id.txt_timeMessage);
        }
        void setData(ChatMessage chatMessage){
            send_message.setText(chatMessage.message);
            timeSent.setText(chatMessage.dateTime);

        }
    }

    static class ReceiveMessageViewHolder extends RecyclerView.ViewHolder{
        public TextView receive_message,timeReceive;
        public ReceiveMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            receive_message = itemView.findViewById(R.id.receive_message);
            timeReceive = itemView.findViewById(R.id.txt_timeMessageReceive);
        }
        void setData(ChatMessage chatMessage){
            receive_message.setText(chatMessage.message);
            timeReceive.setText(chatMessage.dateTime);

        }
    }



}
//    private Context context;
//    private List<message_model> arr_message;
//
//    public MessageApdapter(Context context, List<message_model> arr_message) {
//        this.context = context;
//        this.arr_message = arr_message;
//    }
//
//    public class  ViewHolder extends RecyclerView.ViewHolder{
//        public TextView show_message;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            show_message = itemView.findViewById(R.id.show_message);
//        }
//    }
//
//    @NonNull
//    @Override
//    public MessageApdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
//        return new MessageApdapter.ViewHolder(view);
//    }
//
//    @NonNull
//
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        message_model message = arr_message.get(position);
//        holder.show_message.setText(message.getMessage());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return arr_message.size();
//    }

