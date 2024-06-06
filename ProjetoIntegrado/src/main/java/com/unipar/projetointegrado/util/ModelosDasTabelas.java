package com.unipar.projetointegrado.util;

import com.unipar.projetointegrado.apiinterfaces.ClienteAPI;
import com.unipar.projetointegrado.apiinterfaces.ProdutoAPI;
import models.Cliente;
import models.Produto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ModelosDasTabelas {



    LogConsumoAPI logConsumoAPI = new LogConsumoAPI();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClienteAPI clienteAPI = retrofit.create(ClienteAPI.class);
    ProdutoAPI produtoAPI = retrofit.create(ProdutoAPI.class);
    static DefaultTableModel defaultModelCliente = new DefaultTableModel();
    static DefaultTableModel defaultModelProduto = new DefaultTableModel();

    public void atualizarTbProdutos(){
        produtoAPI.findAll().enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if (response.isSuccessful()) {

                    defaultModelProduto = new DefaultTableModel(new Object[]{"Id", "Descrição", "Preço", "Categoria"}, 0);
                    List<Produto> produtos = response.body();
                    SwingUtilities.invokeLater(() -> {
                        for (Produto produto : produtos) {
                            defaultModelProduto.addRow(new Object[]{produto.getId(), produto.getDescricao(), produto.getPreco(), produto.getCategoria()});
                        }
                    });
                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "ListAll Produtos", "Sucesso");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.err.println("Erro na resposta: " + response.errorBody());
                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "ListAll Produtos", "Erro na resposta: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void atualizarTbClientes(){
        clienteAPI.findAll().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    defaultModelCliente = new DefaultTableModel(new Object[]{"Id", "Nome", "Email", "Cpf"}, 0);
                    List<Cliente> clientes = response.body();
                    SwingUtilities.invokeLater(() -> {
                        for (Cliente cliente : clientes) {
                            defaultModelCliente.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf()});
                        }
                    });


                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "ListAll Clientes", "Sucesso");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "ListAll Clientes", "Erro na resposta: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static DefaultTableModel getTableModelClientes() {
        return defaultModelCliente;
    }

    public static DefaultTableModel getTableModelProdutos() {
        return defaultModelProduto;
    }

}
