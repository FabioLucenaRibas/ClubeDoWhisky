package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.httpClient.LoginTask;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Clients>{

    TextView mActionForgot;
    String password;
    Button signinBtn;
    EditText signinPassword;
    EditText signinUsername;
    Toolbar toolbar;
    String username;
    LoaderManager mLoaderManager;

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

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("password", password);

                mLoaderManager = LoginActivity.this.getSupportLoaderManager();
                mLoaderManager.initLoader(0, bundle, LoginActivity.this);
//                if (username.equals("admin") && password.equals("12345")) {
//                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }
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
            return Boolean.TRUE;
        }
        this.signinUsername.setError(getString(R.string.error_field_empty));
        return Boolean.FALSE;
    }

    public Boolean checkPassword() {
        this.password = this.signinPassword.getText().toString();
        this.signinPassword.setError(null);
        if (this.username.length() != 0) {
            return Boolean.TRUE;
        }
        this.signinPassword.setError(getString(R.string.error_field_empty));
        return Boolean.FALSE;
    }

    public void onBackPressed() {
        LoginActivity.this.startActivity(new Intent(LoginActivity.this, AppActivity.class));
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

    @Override
    public Loader<Clients> onCreateLoader(int id, Bundle args) {
        String username = args.getString("username");
        String password = args.getString("password");
        return new LoginTask(getApplicationContext(), username, password);
    }

    @Override
    public void onLoadFinished(Loader<Clients> loader, Clients data) {
        if (data != null){
            
        }
    }

    @Override
    public void onLoaderReset(Loader<Clients> loader) {

    }
}
