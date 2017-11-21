package com.projeto.clubedowhisky.services;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.classes.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Renato on 20/11/2017.
 */

public interface ClientApiService {

    @GET("client/logar")
    Call<Clients> logar(@Query("email") String email, @Query("password") String password);
}
