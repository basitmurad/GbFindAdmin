package com.example.gbfindadmin;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.OrderViewHolder> {

    private final ArrayList<Order> orders;
    private final String ownerShop;  // Add ownerShop variable


    public ProgressAdapter(ArrayList<Order> orders, String ownerShop) {
        this.orders = orders;
        this.ownerShop = ownerShop;
    }

    @NonNull
    @Override
    public ProgressAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        return new ProgressAdapter.OrderViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ProgressAdapter.OrderViewHolder holder, int position) {
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

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the status to "Progress" locally
                        order.setStatus("Deliver");

                        // Update the status in Firebase
                        DatabaseReference orderRef = FirebaseDatabase.getInstance()
                                .getReference("orders")
                                .child(ownerShop) // Add ownerShop variable reference
                                .child(order.getOrderId()); // Get the orderId

                        orderRef.child("status").setValue("Deliver")
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(v.getContext(), "Order status updated to 'Progress'", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "Failed to update status.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        // Notify the adapter that the item has changed
                        notifyItemChanged(position);
                    }
                });


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
