package com.projeto.clubedowhisky.httpClient;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.projeto.clubedowhisky.classes.Clients;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Renato on 03/12/2017.
 */

public class SaveClientTask extends AsyncTaskLoader<Clients> {

    Clients mClients;
    String mUsername;
    String mEmail;
    String mPassword;
    String mName;
    public SaveClientTask(Context context, String username, String password, String email, String name) {
        super(context);
        mUsername = username;
        mPassword = password;
        mEmail = email;
        mName = name;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mClients == null){
            Log.d("NGVL","forceLoad");
            forceLoad();
        } else {
            deliverResult(mClients);
        }
    }

    @Override
    public Clients loadInBackground() {
        try {
          mClients = ClientParser.save(mUsername, mPassword, mEmail, mName);
        }catch (IOException e){
//            retorno = "Não foi possível inserir o usuário";
            e.printStackTrace();
        }
        return mClients;
    }
}
