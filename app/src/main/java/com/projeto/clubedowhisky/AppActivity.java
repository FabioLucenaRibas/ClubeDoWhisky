package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppActivity extends AppCompatActivity {

    LinearLayout contentScreen;
    RelativeLayout loadingScreen;
    Button loginBtn;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        this.contentScreen = findViewById(R.id.contentScreen);
        this.loadingScreen = findViewById(R.id.loadingScreen);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.signUpBtn = findViewById(R.id.signupBtn);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(AppActivity.this, MainActivity.class));
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                    finish();
                } else {
                    startActivity(new Intent(AppActivity.this, LoginActivity.class));
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                    finish();
                }
            }
        });

        this.signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(AppActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }
}
