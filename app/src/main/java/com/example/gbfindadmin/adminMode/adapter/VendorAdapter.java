package com.example.gbfindadmin.adminMode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.adminMode.model.VendorClass;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.MyHolder> {
    private Context  context;
    private ArrayList<VendorClass> vendorClassArrayList;

    public VendorAdapter(Context context, ArrayList<VendorClass> vendorClassArrayList) {
        this.context = context;
        this.vendorClassArrayList = vendorClassArrayList;
    }

    @NonNull
    @Override
    public VendorAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View view = LayoutInflater.from(context).inflate(R.layout.vendor_layout ,parent ,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return vendorClassArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView vendorName , ownerName ,location;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            vendorName = itemView.findViewById(R.id.vendorShopName);

        }
    }
}
