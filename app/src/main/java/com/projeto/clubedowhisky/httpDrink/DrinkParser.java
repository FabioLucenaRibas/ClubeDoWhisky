package com.projeto.clubedowhisky.httpDrink;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.classes.Drinks;
import com.projeto.clubedowhisky.services.ClientApiService;
import com.projeto.clubedowhisky.services.DrinkApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Renato on 03/12/2017.
 */

public class DrinkParser {

    public static ArrayList<Drinks> allDrinks(int limit) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.6:8080/clubedowhisky-API/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        DrinkApiService api = retrofit.create(DrinkApiService.class);

        ArrayList<Drinks> result = api.allDrinks(limit).execute().body();

        if (result != null) {
            return result;
        }
        return null;
    }
}
