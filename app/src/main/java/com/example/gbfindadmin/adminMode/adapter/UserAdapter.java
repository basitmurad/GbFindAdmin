package com.example.gbfindadmin.adminMode.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbfindadmin.vendorMode.models.UserClass;
import com.example.gbfindadmin.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserClass> userList;

    public UserAdapter(List<UserClass> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserClass user = userList.get(position);
        holder.ownerNameTextView.setText(user.getOwnerName());
        holder.ownerShopNameTextView.setText(user.getOwnerShopName());
        holder.emailTextView.setText(user.getEmail());
        holder.shopAddressTextView.setText(user.getShopAddress());
        holder.ownerContactTextView.setText(user.getOwnerContact());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ownerNameTextView;
        public TextView ownerShopNameTextView;
        public TextView emailTextView;
        public TextView shopAddressTextView;
        public TextView ownerContactTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ownerNameTextView = itemView.findViewById(R.id.ownerNameTextView);
            ownerShopNameTextView = itemView.findViewById(R.id.ownerShopNameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            shopAddressTextView = itemView.findViewById(R.id.shopAddressTextView);
            ownerContactTextView = itemView.findViewById(R.id.ownerContactTextView);
        }
    }
}
