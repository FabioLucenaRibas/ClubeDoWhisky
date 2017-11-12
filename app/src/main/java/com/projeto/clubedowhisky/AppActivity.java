package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AppActivity extends AppCompatActivity {

    LinearLayout contentScreen;
    RelativeLayout loadingScreen;
    Button loginBtn;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        this.contentScreen = (LinearLayout) findViewById(R.id.contentScreen);
        this.loadingScreen = (RelativeLayout) findViewById(R.id.loadingScreen);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppActivity.this.startActivity(new Intent(AppActivity.this, LoginActivity.class));
                finish();
            }
        });
        this.signupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppActivity.this.startActivity(new Intent(AppActivity.this, SignupActivity.class));
                finish();
            }
        });

    }
}
