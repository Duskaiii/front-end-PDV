/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.apiinterfaces;

import retrofit2.Call;
import retrofit2.http.POST;
import models.CredenciaisLogin;
import retrofit2.http.Body;

/**
 *
 * @author Dzkyy
 */
public interface LoginAPI {
    @POST("/auth/login")
    Call<String> login(@Body CredenciaisLogin credentials);
}


