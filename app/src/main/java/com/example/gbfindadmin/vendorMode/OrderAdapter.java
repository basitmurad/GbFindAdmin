package com.example.gbfindadmin.vendorMode;//package com.example.gbfindadmin.vendorMode;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.gbfindadmin.Order;
//import com.example.gbfindadmin.R;
//
//import java.util.ArrayList;
//
//public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
//
//    private final ArrayList<Order> orders;
//
//    public OrderAdapter(ArrayList<Order> orders) {
//        this.orders = orders;
//    }
//
//    @NonNull
//    @Override
//    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
//        return new OrderViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Order order = orders.get(position);
//        holder.accountName.setText("TranscationId: "+order.getAccountName());
//        holder.address.setText("Address: "+order.getAddress());
//
//        holder.textViewStatus.setText("Status :" + order.getStatus());
//        holder.totalPrice.setText("Total Price: " + order.getTotalPrice() + " PKR");
//
//
//        // Display the items in the order
//        StringBuilder itemsDetails = new StringBuilder();
//        for (Order.Item item : order.getItems()) {
//            itemsDetails.append("Name : "+item.getName())
//                    .append(" (Qty: ")
//                    .append(item.getQuantity())
//                    .append(", Price: ")
//                    .append(item.getPrice())
//                    .append(")\n");
//        }
//
//        holder.items.setText(itemsDetails.toString());
//
//        holder.btnDeliver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update the status to "Progress"
//                order.setStatus("Progress");
//
//                // Notify the adapter that the item has changed
//                notifyItemChanged(position);
//
//                // Optionally, show a message
//                Toast.makeText(v.getContext(), "Order status changed to 'Progress'", Toast.LENGTH_SHORT).show();
//            }
//        });    }
//
//    @Override
//    public int getItemCount() {
//        return orders.size();
//    }
//
//    public static class OrderViewHolder extends RecyclerView.ViewHolder {
//        TextView accountName, address, items ,textViewStatus ,totalPrice;
//        Button btnDeliver;
//
//        public OrderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            accountName = itemView.findViewById(R.id.tvAccountName);
//            address = itemView.findViewById(R.id.tvAddress);
//            items = itemView.findViewById(R.id.tvItems);
//            textViewStatus = itemView.findViewById(R.id.tvStatus);
//            totalPrice = itemView.findViewById(R.id.totalPrice);
//
//            btnDeliver = itemView.findViewById(R.id.btnDeliver);
//        }
//    }
//}
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbfindadmin.Order;
import com.example.gbfindadmin.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final ArrayList<Order> orders;

    public OrderAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        return new OrderViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder,  int position) {
        Order order = orders.get(position);
        holder.accountName.setText("TransactionId: " + order.getAccountName());
        holder.address.setText("Address: " + order.getAddress());

        holder.textViewStatus.setText("Status: " + order.getStatus());
        holder.totalPrice.setText("Total Price: " + order.getTotalPrice() + " PKR");

        // Display the items in the order
        StringBuilder itemsDetails = new StringBuilder();
        for (Order.Item item : order.getItems()) {
            itemsDetails.append("Name: " + item.getName())
                    .append(" (Qty: ")
                    .append(item.getQuantity())
                    .append(", Price: ")
                    .append(item.getPrice())
                    .append(")\n");
        }

        holder.items.setText(itemsDetails.toString());

        holder.btnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Deliver Order");
                builder.setMessage("Are you sure you want to mark this order as 'In Progress'?");

                // Positive button - Confirm delivery
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the status to "Progress"
                        order.setStatus("Progress");

                        // Notify the adapter that the item has changed
                        notifyItemChanged(position);

                        // Optionally, show a message
                        Toast.makeText(v.getContext(), "Order status changed to 'Progress'", Toast.LENGTH_SHORT).show();
                    }
                });

                // Negative button - Cancel the action
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Dismiss the dialog if user cancels
                    }
                });

                // Show the dialog
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView accountName, address, items, textViewStatus, totalPrice;
        Button btnDeliver;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.tvAccountName);
            address = itemView.findViewById(R.id.tvAddress);
            items = itemView.findViewById(R.id.tvItems);
            textViewStatus = itemView.findViewById(R.id.tvStatus);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            btnDeliver = itemView.findViewById(R.id.btnDeliver);
        }
    }
}
