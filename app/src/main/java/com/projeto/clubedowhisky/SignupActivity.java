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

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.classes.Users;
import com.projeto.clubedowhisky.httpClient.SaveClientTask;
import com.projeto.clubedowhisky.tabs.MyTicketsFragment;

import java.io.Serializable;

public class SignupActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Clients>{

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
    LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mLoaderManager = SignupActivity.this.getSupportLoaderManager();
//        mLoaderManager.initLoader(0,null, SignupActivity.this);

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

                // TODO CRIAR CHAMADA PARA REGISTRAR CLIENTE

//                if (!SignupActivity.this.verifyRegForm().booleanValue()) {
//                    return;
//                } else{
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("fullname", fullname);
                    bundle.putString("password", password);
                    bundle.putString("email", email);

                    mLoaderManager.initLoader(0,bundle, SignupActivity.this);

//                }

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
            return Boolean.FALSE;
        } else if (this.username.length() < 5) {
            this.signupUsername.setError(getString(R.string.error_small_username));
            return Boolean.FALSE;
        } else if (!helper.isValidLogin(this.username)) {
            this.signupUsername.setError(getString(R.string.error_wrong_format));
            return Boolean.FALSE;
        } else if (this.fullname.length() == 0) {
            this.signupFullname.setError(getString(R.string.error_field_empty));
            return Boolean.FALSE;
        } else if (this.password.length() == 0) {
            this.signupPassword.setError(getString(R.string.error_field_empty));
            return Boolean.FALSE;
        } else if (this.password.length() < 6) {
            this.signupPassword.setError(getString(R.string.error_small_password));
            return Boolean.FALSE;
        } else if (!helper.isValidPassword(this.password)) {
            this.signupPassword.setError(getString(R.string.error_wrong_format));
            return Boolean.FALSE;
        } else if (this.email.length() == 0) {
            this.signupEmail.setError(getString(R.string.error_field_empty));
            return Boolean.FALSE;
        } else {
//            this.signupEmail.setError(getString(R.string.error_wrong_format));
            return Boolean.FALSE;
        }
    }
    public void onBackPressed() {
        SignupActivity.this.startActivity(new Intent(SignupActivity.this, AppActivity.class));
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
        if (args !=null){
            return new SaveClientTask(getApplicationContext(), args.getString("username"),
                    args.getString("password"), args.getString("email"), args.getString("fullname"));
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Clients> loader, Clients data) {
        if (data != null){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("client", data);
            startActivity(i);
        }
    }

    @Override
    public void onLoaderReset(Loader<Clients> loader) {

    }
}
