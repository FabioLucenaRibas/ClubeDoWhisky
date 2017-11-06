package com.projeto.clubedowhisky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecoveryActivity extends AppCompatActivity {

    String email;
    Button mContinueBtn;
    EditText mEmail;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.mEmail = (EditText) findViewById(R.id.PasswordRecoveryEmail);
        this.mContinueBtn = (Button) findViewById(R.id.PasswordRecoveryBtn);
        this.mContinueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RecoveryActivity.this.email = RecoveryActivity.this.mEmail.getText().toString();
            //    if (!App.getInstance().isConnected()) {
            //        Toast.makeText(RecoveryActivity.this.getApplicationContext(), R.string.msg_network_error, 0).show();
            //    } else if (new Helper().isValidEmail(RecoveryActivity.this.email)) {
            //       RecoveryActivity.this.recovery();
            //    } else {
            //        Toast.makeText(RecoveryActivity.this.getApplicationContext(), RecoveryActivity.this.getText(R.string.error_email), 0).show();
            //    }
            }
        });
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
