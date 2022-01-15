package com.example.team_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TKBAdapter extends RecyclerView.Adapter<TKBAdapter.tkbViewHolder> {
    private ArrayList<tkb> tkbArrayList;
    private tkbieu_Activity context;

    public TKBAdapter(ArrayList<tkb> tkbArrayList, tkbieu_Activity context) {
        this.tkbArrayList = tkbArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public tkbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tkb_item,parent,false);
        return new tkbViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull tkbViewHolder holder, int position) {
                tkb tkb = tkbArrayList.get(position);
                if (tkb == null) return;
                holder.tkbTime.setText("Tiết "+tkbArrayList.get(position).getTietBD() +" - Tiết"+ tkbArrayList.get(position).getTietKT()+" /");
                holder.tkbRoom.setText(tkbArrayList.get(position).getIdPhong());
                holder.tkbContent.setText(tkbArrayList.get(position).getMonhoc());
    }
    @Override
    public int getItemCount() {
        return tkbArrayList.size();
    }

    public class tkbViewHolder extends RecyclerView.ViewHolder{
        TextView tkbTime, tkbRoom, tkbContent;
        public tkbViewHolder(@NonNull View itemView) {
            super(itemView);
            tkbTime = itemView.findViewById(R.id.txt_tkbTime);
            tkbRoom = itemView.findViewById(R.id.txt_tkbRoom);
            tkbContent = itemView.findViewById(R.id.text_noidungtkb);
        }
    }
}
