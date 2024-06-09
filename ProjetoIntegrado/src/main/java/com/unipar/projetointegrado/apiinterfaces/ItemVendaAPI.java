/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.apiinterfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import models.ItemVenda;

/**
 *
 * @author Dzkyy
 */
public interface VendaProdutoAPI {
    @POST("/itens")
    Call<ItemVenda> insert(@Body ItemVenda itemVenda);
}
