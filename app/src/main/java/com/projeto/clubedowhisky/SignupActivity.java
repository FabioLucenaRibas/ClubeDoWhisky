package com.projeto.clubedowhisky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private String email;
    private String fullname;
    private String password;
    EditText signupEmail;
    EditText signupFullname;
    Button signupJoinBtn;
    EditText signupPassword;
    EditText signupUsername;
    Toolbar toolbar;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.signupUsername = (EditText) findViewById(R.id.signupUsername);
        this.signupFullname = (EditText) findViewById(R.id.signupFullname);
        this.signupPassword = (EditText) findViewById(R.id.signupPassword);
        this.signupEmail = (EditText) findViewById(R.id.signupEmail);
        this.signupJoinBtn = (Button) findViewById(R.id.signupJoinBtn);
        this.signupJoinBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SignupActivity.this.username = SignupActivity.this.signupUsername.getText().toString();
                SignupActivity.this.fullname = SignupActivity.this.signupFullname.getText().toString();
                SignupActivity.this.password = SignupActivity.this.signupPassword.getText().toString();
                SignupActivity.this.email = SignupActivity.this.signupEmail.getText().toString();

                if (!SignupActivity.this.verifyRegForm().booleanValue()) {
                    return;
                }


            }
        });
    }

    public Boolean verifyRegForm() {
        this.signupUsername.setError(null);
        this.signupFullname.setError(null);
        this.signupPassword.setError(null);
        this.signupEmail.setError(null);
        Helper helper = new Helper();
        if (this.username.length() == 0) {
            this.signupUsername.setError(getString(R.string.error_field_empty));
            return Boolean.valueOf(false);
        } else if (this.username.length() < 5) {
            this.signupUsername.setError(getString(R.string.error_small_username));
            return Boolean.valueOf(false);
        } else if (!helper.isValidLogin(this.username)) {
            this.signupUsername.setError(getString(R.string.error_wrong_format));
            return Boolean.valueOf(false);
        } else if (this.fullname.length() == 0) {
            this.signupFullname.setError(getString(R.string.error_field_empty));
            return Boolean.valueOf(false);
        } else if (this.password.length() == 0) {
            this.signupPassword.setError(getString(R.string.error_field_empty));
            return Boolean.valueOf(false);
        } else if (this.password.length() < 6) {
            this.signupPassword.setError(getString(R.string.error_small_password));
            return Boolean.valueOf(false);
        } else if (!helper.isValidPassword(this.password)) {
            this.signupPassword.setError(getString(R.string.error_wrong_format));
            return Boolean.valueOf(false);
        } else if (this.email.length() == 0) {
            this.signupEmail.setError(getString(R.string.error_field_empty));
            return Boolean.valueOf(false);
        } else if (helper.isValidEmail(this.email)) {
            return Boolean.valueOf(true);
        } else {
            this.signupEmail.setError(getString(R.string.error_wrong_format));
            return Boolean.valueOf(false);
        }
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
