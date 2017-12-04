package com.projeto.clubedowhisky.services;

import com.projeto.clubedowhisky.classes.Drinks;
import com.projeto.clubedowhisky.loadResults.DrinksLoadResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Renato on 03/12/2017.
 */

public interface DrinkApiService {

    @GET("drink/allDrinks/{limit}")
    Call<ArrayList<Drinks>> allDrinks(@Path("limit") int limit);
}
