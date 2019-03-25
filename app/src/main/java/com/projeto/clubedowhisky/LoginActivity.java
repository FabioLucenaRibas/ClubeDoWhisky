package com.projeto.clubedowhisky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView mActionForgot;
    String password;
    Button signInBtn;
    EditText signInPassword;
    EditText signInEmail;
    Toolbar toolbar;
    String email;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.toolbar = findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        this.signInEmail = findViewById(R.id.signInEmail);
        this.signInPassword = findViewById(R.id.signinPassword);
        this.signInBtn = findViewById(R.id.signinBtn);
        this.signInBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email = signInEmail.getText().toString();
                password = signInPassword.getText().toString();
                if (verifyRegForm()) {
                    login();
                }
            }
        });
        this.mActionForgot = findViewById(R.id.actionForgot);
        this.mActionForgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this, RecoveryActivity.class), 1);
            }
        });
    }

    public void login() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Log.w("AUTH", "Falha ao efetuar o Login: ", task.getException());
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    public Boolean verifyRegForm() {
        this.signInEmail.setError(null);
        this.signInPassword.setError(null);
        Helper helper = new Helper();

        Boolean retorno = Boolean.TRUE;

        if (TextUtils.isEmpty(email)) {
            this.signInEmail.setError(getString(R.string.error_field_empty));
            retorno = Boolean.FALSE;
        } else if (!helper.isValidEmail(email)) {
            this.signInEmail.setError(getString(R.string.error_wrong_format));
            retorno = Boolean.FALSE;
        }

        if (TextUtils.isEmpty(password)) {
            this.signInPassword.setError(getString(R.string.error_field_empty));
            retorno = Boolean.FALSE;
        } else if (this.password.length() < 8) {
            this.signInPassword.setError(getString(R.string.error_small_password));
            retorno = Boolean.FALSE;
        } else if (!helper.isValidPassword(this.password)) {
            this.signInPassword.setError(getString(R.string.error_wrong_format));
            retorno = Boolean.FALSE;
        }
        return retorno;
    }

    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this, AppActivity.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String pEmail = data.getStringExtra("email");
                Snackbar.make(LoginActivity.this.getCurrentFocus(), getString(R.string.redefine_password) + " " + pEmail, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}
