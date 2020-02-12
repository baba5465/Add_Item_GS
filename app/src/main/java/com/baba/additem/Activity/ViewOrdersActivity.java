package com.baba.additem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.baba.additem.Activity.SelectOptionActivity;
import com.baba.additem.Adapter.OrderAdapter;
import com.baba.additem.Model.Orders;
import com.baba.additem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewOrdersActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<Orders> ordersList = new ArrayList<>();
    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdapter(ordersList);
        recyclerView.setAdapter(adapter);
        getData();

    }
    private void getData(){
        ordersList.clear();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Orders od = ds.getValue(Orders.class);
                    ordersList.add(od);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SelectOptionActivity.class));
        finishAffinity();
        finish();
    }
}
