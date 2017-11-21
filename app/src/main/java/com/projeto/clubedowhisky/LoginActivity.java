package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.httpClient.ClientParser;
import com.projeto.clubedowhisky.services.ClientApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextView mActionForgot;
    String password;
    Button signinBtn;
    EditText signinPassword;
    EditText signinUsername;
    Toolbar toolbar;
    String username;
    Retrofit retrofit;

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

                try {
                    logar(username, password);
                }catch (Exception e){
                    e.getMessage();
                }
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

    private void logar(String username, String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.6:8080/clubedowhisky-API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientApiService api = retrofit.create(ClientApiService.class);
        Call<Clients> clientsCall = api.logar(username, password);

        clientsCall.enqueue(new Callback<Clients>() {
            @Override
            public void onResponse(Call<Clients> call, Response<Clients> response) {
//                Log.d("Url","response.raw().request().url();"+response.raw().request().url());
                if (response.isSuccessful()){
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Clients> call, Throwable t) {
                Toast.makeText(LoginActivity.this.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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

}
