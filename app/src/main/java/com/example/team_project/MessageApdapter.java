package com.example.team_project;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageApdapter extends RecyclerView.Adapter<MessageApdapter.ViewHolder> {

    private Context context;
    private List<message_model> arr_message;

    public MessageApdapter(Context context, List<message_model> arr_message) {
        this.context = context;
        this.arr_message = arr_message;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
        }
    }

    @NonNull
    @Override
    public MessageApdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
        return new MessageApdapter.ViewHolder(view);
    }

    @NonNull

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        message_model message = arr_message.get(position);
        holder.show_message.setText(message.getMessage());
    }


    @Override
    public int getItemCount() {
        return arr_message.size();
    }
}
