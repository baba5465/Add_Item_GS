package com.baba.additem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.baba.additem.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SelectOptionActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);

        Button viewOrder = findViewById(R.id.view_order);
        Button addItems = findViewById(R.id.add_items);
        imageView = findViewById(R.id.option_image);

        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewOrdersActivity.class));
                finishAffinity();
                finish();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
                finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), imageView);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     public boolean onMenuItemClick(MenuItem item) {
                         switch (item.getItemId()) {
                             case R.id.logout:
                                 AuthUI.getInstance()
                                         .signOut(getApplicationContext())
                                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                                             public void onComplete(@NonNull Task<Void> task) {
                                                 Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                                                 startActivity(new Intent(getApplicationContext(), RegisterAdminActivity.class));
                                                 finish();
                                             }
                                         });
                                 return true;
                         }

                         return false;
                     }
                 }
                );
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.show();
            }
        });
    }
}
