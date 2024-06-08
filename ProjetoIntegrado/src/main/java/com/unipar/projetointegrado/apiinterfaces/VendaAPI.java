/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.apiinterfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import models.Venda;
import retrofit2.http.PUT;

/**
 *
 * @author Dzkyy
 */
public interface VendaAPI {
    @POST("/vendas")
    Call<Venda> insert(@Body Venda venda);
    
    @PUT("/calcular-total")
    Call<Double> calcular(@Body Double valor);
}
