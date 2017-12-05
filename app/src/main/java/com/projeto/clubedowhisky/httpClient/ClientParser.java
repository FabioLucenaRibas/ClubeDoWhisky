package com.projeto.clubedowhisky.httpClient;

import com.google.gson.Gson;
import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.services.ClientApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Renato on 20/11/2017.
 */

public class ClientParser {

    public static Clients logar(String username, String password) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.96:8080/clubedowhisky-API/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ClientApiService api = retrofit.create(ClientApiService.class);

        Clients clients = api.logar(username, password).execute().body();

        if (clients != null) {
            return clients;
        }
        return null;
    }

    public static Clients save(String username, String password, String email, String name) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.96:8080/clubedowhisky-API/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ClientApiService api = retrofit.create(ClientApiService.class);

        Clients clients = api.save(username, password, email, name).execute().body();

        if (clients != null) {
            return clients;
        }
        return null;
    }
}
