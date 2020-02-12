package com.baba.additem.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.baba.additem.Model.ItemDetails;
import com.baba.additem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 1;
    private Uri imageUrl = null;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    FirebaseUser user;

    String ImageUrl = null;

    ImageView imageView;
    EditText itemName, itemPrice;
    private Button button;
    Spinner spinner;
    String[] category = new String[]{"Select Category", "Fruits & Vegetables", "Foodgrains, Oil & Masala", "Bakery, Cakes & Dairy", "Beverages", "Snacks", "Beauty", "Household"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        itemName = findViewById(R.id.itemname);
        itemPrice = findViewById(R.id.itemprice);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, category);
        spinner.setAdapter(adapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChoose();
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closekeyboard();
                saveImage();
            }
        });
    }

    private void showFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUrl = data.getData();

            Picasso.get().load(imageUrl).into(imageView);

        }
    }

    private void saveImage() {
        String item_name = itemName.getText().toString().trim();
        String category = spinner.getSelectedItem().toString().trim();
        mStorageRef = FirebaseStorage.getInstance().getReference("Category/" + category);


        if (imageUrl != null) {

            final StorageReference fileRefrence = mStorageRef.child(item_name + "." + getFileExtension(imageUrl));

            mUploadTask = fileRefrence.putFile(imageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // uploadProgress.setProgress(0);
                                }
                            }, 500);

                            fileRefrence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ImageUrl = uri.toString();
                                    uploadData();
                                }
                            });


                        }
                    });

        } else {
            Toast.makeText(getApplicationContext(), "Please choose an image to continue.", Toast.LENGTH_SHORT).show();
        }


    }

    private void uploadData() {
        String itemname = itemName.getText().toString().trim();
        String itemprice = itemPrice.getText().toString().trim();
        String imageurl = ImageUrl;
        String category = spinner.getSelectedItem().toString().trim();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Category/" + category);
        String itemid = mDatabaseRef.push().getKey();

        final ItemDetails idt = new ItemDetails(itemname, itemprice, imageurl, category, itemid);
        mDatabaseRef.child(itemid).setValue(idt).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
                    Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void closekeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SelectOptionActivity.class));
        finishAffinity();
        finish();
    }
}
