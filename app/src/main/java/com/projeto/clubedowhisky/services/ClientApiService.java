package com.projeto.clubedowhisky.services;

import com.projeto.clubedowhisky.classes.Clients;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Renato on 20/11/2017.
 */

public interface ClientApiService {

    @GET("client/logar")
    Call<Clients> logar(@Query("username") String username, @Query("password") String password);

    @GET("client/save")
    Call<Clients> save(@Query("username") String username, @Query("password") String password, @Query("email") String email, @Query("name") String name);
}
