package com.unipar.projetointegrado.apiinterfaces;

import models.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ClienteAPI {

    @GET("/clientes/all")
    Call<List<Cliente>> findAll();

    @POST("/clientes")
    Call<Cliente> insert(@Body Cliente cliente);

}
