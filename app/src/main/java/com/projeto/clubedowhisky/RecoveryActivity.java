package com.projeto.clubedowhisky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryActivity extends AppCompatActivity {

    String email;
    Button mContinueBtn;
    EditText mEmail;
    Toolbar toolbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        this.toolbar = findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        this.mEmail = findViewById(R.id.PasswordRecoveryEmail);
        this.mContinueBtn = findViewById(R.id.PasswordRecoveryBtn);
        this.mContinueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email = mEmail.getText().toString();
                recovery();
            }
        });
    }

    private void recovery() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("email", email);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Snackbar.make(RecoveryActivity.this.getCurrentFocus(), email + " " + getString(R.string.does_not_exist), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public void onBackPressed() {
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
