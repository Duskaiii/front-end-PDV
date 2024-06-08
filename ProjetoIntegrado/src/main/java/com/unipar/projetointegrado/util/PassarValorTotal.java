/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.util;

import com.unipar.projetointegrado.apiinterfaces.VendaAPI;
import javax.swing.*;

import models.Venda;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Dzkyy
 */
public class PassarValorTotal {
    
    LogConsumoAPI logConsumoAPI = new LogConsumoAPI();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static JTextArea textArea = new JTextArea();
    
    VendaAPI vendaAPI = retrofit.create(VendaAPI.class);
    

    public void atualizarValorTotal(Double valor){
        Call<Double> call = vendaAPI.calcular(valor);

        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response){

                if(response.isSuccessful() && response.body() !=null){

                    textArea.setText(response.body().toString());
                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "Atualizar Valor Total", "Sucesso");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else{

                    System.err.println("Erro na resposta: "+response.errorBody());

                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "Atualizar Valor Total", "Erro na resposta: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


            }
            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
