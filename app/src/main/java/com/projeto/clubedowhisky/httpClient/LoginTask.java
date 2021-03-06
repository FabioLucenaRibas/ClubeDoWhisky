package com.projeto.clubedowhisky.httpClient;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.projeto.clubedowhisky.classes.Clients;

import java.io.IOException;

/**
 * Created by renato.silva on 21/11/2017.
 */

public class LoginTask extends AsyncTaskLoader<Clients> {

    Clients clients;
    String mUsername;
    String mPassword;

    public LoginTask(Context context, String username, String password) {
        super(context);
        mUsername = username;
        mPassword = password;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (clients == null){
            Log.d("NGVL","forceLoad");
            forceLoad();
        } else {
            deliverResult(clients);
        }
    }

    @Override
    public Clients loadInBackground() {
        try {
            clients = ClientParser.logar(mUsername, mPassword);
        }catch (IOException e){
            e.printStackTrace();
        }

        return clients;
    }
}
