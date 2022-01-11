package com.example.team_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final List<User> users;
    private Context context;
    private final UserListener userListener;
    private int layout;

    public UserAdapter(List<User> users, Context context, UserListener userListener) {
        this.users = users;
        this.context = context;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_userlist,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
                holder.setUserData(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView userImage;
        RelativeLayout mainLayout;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.txt_lineusername);
            userImage = itemView.findViewById(R.id.lineimg_user);
            mainLayout = itemView.findViewById(R.id.mainChatLayout);
        }
        void setUserData(User user){
            userImage.setImageResource(user.image);
            userName.setText(user.name);
            mainLayout.setOnClickListener(v -> userListener.onUserClicked(user));

        }
    }
}
