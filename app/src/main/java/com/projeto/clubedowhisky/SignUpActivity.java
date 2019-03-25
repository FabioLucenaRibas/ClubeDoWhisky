package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    EditText signUpEmail;
    EditText signUpName;
    Button signUpJoinBtn;
    EditText signUpPassword;
    EditText signUpConfirmPassword;
    Toolbar toolbar;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.toolbar = findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.signUpName = findViewById(R.id.signUpName);
        this.signUpEmail = findViewById(R.id.signUpEmail);
        this.signUpPassword = findViewById(R.id.signUpPassword);
        this.signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword);
        this.signUpJoinBtn = findViewById(R.id.signUpJoinBtn);
        this.progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        this.signUpJoinBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = signUpName.getText().toString();
                email = signUpEmail.getText().toString();
                password = signUpPassword.getText().toString();
                confirmPassword = signUpConfirmPassword.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if (verifyRegForm()) {
                    progressBar.setVisibility(View.VISIBLE);
                    signUpJoinBtn.setEnabled(false);
                    toRegister();
                    progressBar.setVisibility(View.GONE);
                    signUpJoinBtn.setEnabled(true);
                }
            }
        });
    }

    private void toRegister() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            FirebaseUser user = mAuth.getCurrentUser();
                            user.updateProfile(profileUpdates);
                            user.sendEmailVerification();
                            login();
                        } else {
                            Snackbar.make(SignUpActivity.this.getCurrentFocus(), getString(R.string.error_service_temporarily), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                });

    }

    private void login() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }

    public Boolean verifyRegForm() {
        this.signUpName.setError(null);
        this.signUpEmail.setError(null);
        this.signUpPassword.setError(null);
        this.signUpConfirmPassword.setError(null);
        Helper helper = new Helper();

        Boolean retorno = Boolean.TRUE;

        if (TextUtils.isEmpty(name)) {
            this.signUpName.setError(getString(R.string.error_field_empty));
            retorno = Boolean.FALSE;
        }

        if (TextUtils.isEmpty(password)) {
            this.signUpPassword.setError(getString(R.string.error_field_empty));
            retorno = Boolean.FALSE;
        } else if (this.password.length() < 8) {
            this.signUpPassword.setError(getString(R.string.error_small_password));
            retorno = Boolean.FALSE;
        } else if (!helper.isValidPassword(this.password)) {
            this.signUpPassword.setError(getString(R.string.error_wrong_format));
            retorno = Boolean.FALSE;
        } else if (!password.equals(confirmPassword)) {
            this.signUpConfirmPassword.setError(getString(R.string.error_password_not_match));
            retorno = Boolean.FALSE;
        }

        if (TextUtils.isEmpty(email)) {
            this.signUpEmail.setError(getString(R.string.error_field_empty));
            retorno = Boolean.FALSE;
        } else if (!helper.isValidEmail(email)) {
            this.signUpEmail.setError(getString(R.string.error_wrong_format));
            retorno = Boolean.FALSE;
        }

        return retorno;
    }

    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this, AppActivity.class));
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
}
