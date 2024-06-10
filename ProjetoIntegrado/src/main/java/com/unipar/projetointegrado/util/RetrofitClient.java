/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.AuthInterceptor;
import models.TokenManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


/**
 *
 * @author Dzkyy
 */
public class RetrofitClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static Retrofit retrofit;
    private static String authToken;

    public static void setToken(String token) {
        authToken = token;
        if (retrofit != null) {
            rebuildRetrofit();
            System.out.println("Token atualizado" + authToken);
        }
    }

    private static void rebuildRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + authToken)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            rebuildRetrofit();
        }
        return retrofit;
    }


}

