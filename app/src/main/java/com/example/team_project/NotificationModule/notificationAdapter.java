package com.example.team_project.NotificationModule;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.ChatModule.User;
import com.example.team_project.R;

import java.util.ArrayList;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.notificationViewHolder>{
    private ArrayList<notification> listNoti = new ArrayList<>();
    private notification_Activity context;

    public notificationAdapter(ArrayList<notification> listNoti,notification_Activity context) {
        this.listNoti = listNoti;
        this.context = context;
    }

    @NonNull
    @Override
    public notificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_tem,parent,false);
        return new notificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationViewHolder holder, int position) {
            notification notification = listNoti.get(position);
            if (notification == null)
                return;
            holder.notiName.setText(listNoti.get(position).getNguoiGui());
            holder.notiTitle.setText(listNoti.get(position).getTieuDe());
            holder.MainlineNoti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,notification_details_Activity.class);
                    intent.putExtra("NotiDetails",notification);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listNoti.size();
    }

    public class notificationViewHolder extends RecyclerView.ViewHolder{
        TextView notiName,notiTitle;
        RelativeLayout MainlineNoti;
        public notificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notiName = itemView.findViewById(R.id.noti_name);
            notiTitle = itemView.findViewById(R.id.noti_titile);
            MainlineNoti = itemView.findViewById(R.id.noti_item);
        }
    }


}
