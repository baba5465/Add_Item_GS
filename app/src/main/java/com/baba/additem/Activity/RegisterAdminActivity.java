package com.baba.additem.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baba.additem.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class RegisterAdminActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 007;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
        SignInButton signInBtn = findViewById(R.id.signin_btn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInOptions();
            }
        });
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .build(), MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterAdminActivity.this, SelectOptionActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            //Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterAdminActivity.this, SelectOptionActivity.class);
            finishAffinity();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}
