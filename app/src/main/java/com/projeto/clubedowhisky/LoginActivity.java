package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView mActionForgot;
    String password;
    Button signinBtn;
    EditText signinPassword;
    EditText signinUsername;
    Toolbar toolbar;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.signinUsername = (EditText) findViewById(R.id.signinUsername);
        this.signinPassword = (EditText) findViewById(R.id.signinPassword);
        this.signinBtn = (Button) findViewById(R.id.signinBtn);
        this.signinBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.username = LoginActivity.this.signinUsername.getText().toString();
                LoginActivity.this.password = LoginActivity.this.signinPassword.getText().toString();

                if (username.equals("admin") && password.equals("12345")) {
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                //     if (!App.getInstance().isConnected()) {
                //         Toast.makeText(LoginActivity.this.getApplicationContext(), R.string.msg_network_error, 0).show();
                //     } else if (LoginActivity.this.checkUsername().booleanValue() && LoginActivity.this.checkPassword().booleanValue()) {
                //         LoginActivity.this.signin();
                //     }
            }
        });
        this.mActionForgot = (TextView) findViewById(R.id.actionForgot);
        this.mActionForgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), RecoveryActivity.class));
            }
        });
    }

    public Boolean checkUsername() {
        this.username = this.signinUsername.getText().toString();
        this.signinUsername.setError(null);
        Helper helper = new Helper();
        if (this.username.length() != 0) {
            return Boolean.valueOf(true);
        }
        this.signinUsername.setError(getString(R.string.error_field_empty));
        return Boolean.valueOf(false);
    }

    public Boolean checkPassword() {
        this.password = this.signinPassword.getText().toString();
        this.signinPassword.setError(null);
        if (this.username.length() != 0) {
            return Boolean.valueOf(true);
        }
        this.signinPassword.setError(getString(R.string.error_field_empty));
        return Boolean.valueOf(false);
    }

    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
