package com.example.gbfindadmin;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbfindadmin.vendorMode.OrderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeliverActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public OrderAdapter orderAdapter; // OrderAdapter reference
    private ArrayList<Order> orders;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        String ownerShop = getIntent().getStringExtra("ownerShopName");
        assert ownerShop != null;  // Initialize the RecyclerView
        recyclerView = findViewById(R.id.activeRecycler11111);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(orders,ownerShop); // Initialize OrderAdapter
        recyclerView.setAdapter(orderAdapter);


        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("orders").child(ownerShop);

        ordersRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orders.clear(); // Clear the list before adding new data
                if (snapshot.exists()) {
                    Toast.makeText(DeliverActivity.this, "Orders found for this shop.", Toast.LENGTH_SHORT).show();

                    // Iterate through the children of the snapshot to get order details
                    for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                        String orderId = orderSnapshot.getKey(); // Get the order ID
                        String status = orderSnapshot.child("status").getValue(String.class);

                        // Check if the status is "awaiting"
                        if ("Deliver".equals(status)) {
                            Long totalPriceLong = orderSnapshot.child("totalPrice").getValue(Long.class);
                            String totalPrice = totalPriceLong != null ? totalPriceLong.toString() : null;
                            String transactionId = orderSnapshot.child("transcationId").getValue(String.class);

                            // Logging the main order details
                            Log.d(TAG, "Order ID: " + orderId + ", Total Price: " + totalPrice + ", Transaction ID: " + transactionId + ", Status: " + status);

                            ArrayList<Order.Item> items = new ArrayList<>();
                            for (DataSnapshot itemSnapshot : orderSnapshot.child("items").getChildren()) {
                                String itemId = itemSnapshot.getKey();
                                String itemName = itemSnapshot.child("name").getValue(String.class);

                                // Attempt to retrieve price as a String and parse it to double
                                String priceString = itemSnapshot.child("price").getValue(String.class);
                                double itemPrice = 0.0; // Default value

                                try {
                                    itemPrice = Double.parseDouble(priceString);
                                } catch (NumberFormatException e) {
                                    Log.e(TAG, "Error parsing price: " + priceString, e);
                                }

                                int itemQuantity = itemSnapshot.child("quantity").getValue(Integer.class);

                                // Create an Order.Item object
                                Order.Item item = new Order.Item(itemId, itemName, itemPrice, itemQuantity);
                                items.add(item);

                                // Logging each item detail
                                Log.d(TAG, "Item: " + itemName + ", Price: " + itemPrice + ", Quantity: " + itemQuantity);
                            }

                            // Create an Order object and add it to the list
                            Order order = new Order(orderId, orderSnapshot.child("phone").getValue(String.class),
                                    orderSnapshot.child("accountNumber").getValue(String.class),
                                    orderSnapshot.child("address").getValue(String.class),
                                    status, totalPrice, items);
                            orders.add(order);
                        }
                    }

                    // Notify the adapter that the data has changed
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DeliverActivity.this, "No orders found for this shop.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DeliverActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}