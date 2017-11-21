package com.projeto.clubedowhisky.httpClient;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.services.ClientApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Renato on 20/11/2017.
 */

public class ClientParser {

    public static Clients logar(String username, String password) throws IOException{
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.6:8080/clubedowhisky-API/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ClientApiService api = retrofit.create(ClientApiService.class);

        Clients clients = api.logar(username,password).execute().body();

        if (client != null){
            return clients;
        }
        return  null;
    }
}
