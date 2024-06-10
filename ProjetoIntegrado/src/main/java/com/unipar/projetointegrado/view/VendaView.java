/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.unipar.projetointegrado.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unipar.projetointegrado.apiinterfaces.ItemVendaAPI;
import com.unipar.projetointegrado.apiinterfaces.VendaAPI;
import com.unipar.projetointegrado.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import models.Cliente;

import models.ItemVenda;
import models.Produto;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import models.Venda;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Dzkyy
 */
public class VendaView extends javax.swing.JFrame {

    /**
     * Creates new form VendaView
     */
    private String quantidadeProduto;
    List<Produto> produtos = new ArrayList<>();
    List<Produto> listParaCalcularTotal = new ArrayList<>();
    Venda venda = new Venda();
    PassarCliente clientePassado = new PassarCliente();
    PassarProduto produtoPassado = new PassarProduto();
    LogConsumoAPI logConsumoAPI = new LogConsumoAPI();
    DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[]{"Cod", "Descrição", "Categoria", "Valor unit", "Qtd", "Valor total"}, 0);
    ModelosDasTabelas tbModels = new ModelosDasTabelas();

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    VendaAPI vendaAPI = retrofit.create(VendaAPI.class);
    ItemVendaAPI itemVendaAPI = retrofit.create(ItemVendaAPI.class);

    public VendaView() {
        initComponents();
        setLocationRelativeTo(null);
        ((JSpinner.DefaultEditor) spinnerQtd.getEditor()).getTextField().setEditable(true);
        tbProdutos.setModel(defaultTableModel);
        tbProdutos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                    atualizarPrecoTotal();
                }
            }
        });
        tbProdutos.setDefaultEditor(Object.class, null);

        ScheduledExecutorService agendar = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            public void run() {
                tbModels.atualizarTbClientes();
                tbModels.atualizarTbProdutos();
            }
        };
        agendar.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.MINUTES);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btFinalizaVenda = new javax.swing.JButton();
        btLimpaVenda = new javax.swing.JButton();
        txtTotalVenda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        btAddProduto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btRemoverProduto = new javax.swing.JButton();
        btSelecionaProduto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtObservacao = new java.awt.TextField();
        txtNomeCliente = new javax.swing.JTextField();
        txtCodCliente = new javax.swing.JTextField();
        btSelecionaCliente = new javax.swing.JButton();
        spinnerQtd = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbProdutos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbProdutos.setForeground(new java.awt.Color(102, 102, 102));
        tbProdutos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tbProdutosComponentAdded(evt);
            }
        });
        jScrollPane2.setViewportView(tbProdutos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)
                                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btFinalizaVenda.setBackground(new java.awt.Color(0, 102, 0));
        btFinalizaVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btFinalizaVenda.setForeground(new java.awt.Color(255, 255, 255));
        btFinalizaVenda.setText("Finalizar venda");
        btFinalizaVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btFinalizaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizaVendaActionPerformed(evt);
            }
        });

        btLimpaVenda.setBackground(new java.awt.Color(255, 51, 51));
        btLimpaVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btLimpaVenda.setForeground(new java.awt.Color(255, 255, 255));
        btLimpaVenda.setText("Limpar");
        btLimpaVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btLimpaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpaVendaActionPerformed(evt);
            }
        });

        txtTotalVenda.setEditable(false);
        txtTotalVenda.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalVenda.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalVenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalVenda.setText("0,00");
        txtTotalVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtTotalVenda.setEnabled(false);


        jLabel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total geral:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtCod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCod.setForeground(new java.awt.Color(255, 255, 255));
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCod.setEnabled(false);

        txtDescricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(255, 255, 255));
        txtDescricao.setToolTipText("");
        txtDescricao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDescricao.setEnabled(false);

        btAddProduto.setBackground(new java.awt.Color(0, 102, 0));
        btAddProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btAddProduto.setForeground(new java.awt.Color(255, 255, 255));
        btAddProduto.setText("Adicionar");
        btAddProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddProdutoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quantidade:");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btRemoverProduto.setBackground(new java.awt.Color(255, 51, 51));
        btRemoverProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btRemoverProduto.setForeground(new java.awt.Color(255, 255, 255));
        btRemoverProduto.setText("Remover");
        btRemoverProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverProdutoActionPerformed(evt);
            }
        });

        btSelecionaProduto.setBackground(new java.awt.Color(0, 0, 102));
        btSelecionaProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btSelecionaProduto.setForeground(new java.awt.Color(255, 255, 255));
        btSelecionaProduto.setText("Selecionar Produtos");
        btSelecionaProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btSelecionaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionaProdutoActionPerformed(evt);
            }
        });

        jLabel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Observação:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacaoActionPerformed(evt);
            }
        });

        txtNomeCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNomeCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtNomeCliente.setToolTipText("");
        txtNomeCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNomeCliente.setEnabled(false);

        txtCodCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCodCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtCodCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCodCliente.setEnabled(false);
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });

        btSelecionaCliente.setBackground(new java.awt.Color(0, 0, 102));
        btSelecionaCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btSelecionaCliente.setForeground(new java.awt.Color(255, 255, 255));
        btSelecionaCliente.setText("Selecionar Cliente");
        btSelecionaCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btSelecionaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionaClienteActionPerformed(evt);
            }
        });

        spinnerQtd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerQtd.setEditor(new javax.swing.JSpinner.NumberEditor(spinnerQtd, ""));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btSelecionaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(btFinalizaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(btLimpaVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDescricao))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(btAddProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btSelecionaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNomeCliente))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(153, 153, 153)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btSelecionaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSelecionaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(spinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btFinalizaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btLimpaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarPrecoTotal() {
        produtos.clear();
        listParaCalcularTotal.clear();

        for (int i = 0; i < tbProdutos.getRowCount(); i++) {
            produtos.add(new Produto(Long.parseLong(tbProdutos.getValueAt(i, 0).toString()),
                    tbProdutos.getValueAt(i, 1).toString(), Double.parseDouble(tbProdutos.getValueAt(i, 3).toString()),
                    tbProdutos.getValueAt(i, 2).toString()));
        }

        for (int i = 0; i < tbProdutos.getRowCount(); i++) {
            listParaCalcularTotal.add(new Produto(null, null, Double.parseDouble(tbProdutos.getValueAt(i, 5).toString()), null));
        }

        Call<Double> call = vendaAPI.calcular(listParaCalcularTotal);

        call.clone().enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful() && response.body() != null) {
                    txtTotalVenda.setText(response.body().toString());
                    try {
                        logConsumoAPI.registrarConsumo(new Date().getTime(), "Atualizar Valor Total", "Sucesso");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                try {
                    logConsumoAPI.registrarConsumo(new Date().getTime(), "Atualizar Valor Total", "Erro na resposta: " + t.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void btLimpaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpaVendaActionPerformed
        defaultTableModel.setRowCount(0);
        txtNomeCliente.setText("");
        txtCodCliente.setText("");
        txtCod.setText("");
        txtDescricao.setText("");
        txtObservacao.setText("");
        spinnerQtd.setValue(1);
        clientePassado.resetCliente();
        produtoPassado.resetProduto();
        atualizarPrecoTotal();
    }//GEN-LAST:event_btLimpaVendaActionPerformed

    private void btSelecionaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionaProdutoActionPerformed
        new SelecionaProdutoView().setVisible(true);
    }//GEN-LAST:event_btSelecionaProdutoActionPerformed

    private void btAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddProdutoActionPerformed
        if (!(txtDescricao.getText().isBlank() || txtDescricao.getText().equals(""))) {

            quantidadeProduto = spinnerQtd.getValue().toString();

            defaultTableModel.addRow(new Object[]{produtoPassado.id.toString(),
                    produtoPassado.descricao.toString(), produtoPassado.categoria.toString(), produtoPassado.preco.toString(),
                    quantidadeProduto, produtoPassado.preco * Double.parseDouble(quantidadeProduto)});

            tbProdutos.setModel(defaultTableModel);

            txtCod.setText("");
            txtDescricao.setText("");
            spinnerQtd.setValue(1);

        }
    }//GEN-LAST:event_btAddProdutoActionPerformed

    private void btRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverProdutoActionPerformed
        if (!(tbProdutos.getSelectedRow() == -1)) {
            defaultTableModel.removeRow(tbProdutos.getSelectedRow());
        }
    }//GEN-LAST:event_btRemoverProdutoActionPerformed


    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

        if (clientePassado.id != null) {
            txtCodCliente.setText(clientePassado.id.toString());
            txtNomeCliente.setText(clientePassado.nome);
        }

        if (produtoPassado.id != null) {
            txtCod.setText(produtoPassado.id.toString());
            txtDescricao.setText(produtoPassado.descricao);
        }

    }//GEN-LAST:event_formWindowGainedFocus

    private void tbProdutosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tbProdutosComponentAdded

    }//GEN-LAST:event_tbProdutosComponentAdded

    private void txtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacaoActionPerformed

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void btSelecionaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionaClienteActionPerformed
        new SelecionaClienteView().setVisible(true);
    }//GEN-LAST:event_btSelecionaClienteActionPerformed

    private void btFinalizaVendaActionPerformed(java.awt.event.ActionEvent evt) {
        new Thread(new Runnable() {
            public void run() {
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

                System.out.println(date);

                venda.setCliente(new Cliente(clientePassado.id, clientePassado.nome, clientePassado.cpf, clientePassado.email));
                venda.setValorTotal(Double.parseDouble(txtTotalVenda.getText()));
                venda.setData(date);
                venda.setObservacoes(txtObservacao.getText());

                Call<Venda> call = vendaAPI.insert(venda);

                call.enqueue(new Callback<Venda>() {
                    @Override
                    public void onResponse(Call<Venda> call, Response<Venda> response) {
                        gerarItemVenda(response.body(), date);
                        if (response.isSuccessful()) {
                            try {
                                logConsumoAPI.registrarConsumo(date.getTime(), "Insert Venda", "Sucesso");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Venda> call, Throwable t) {
                        try {
                            logConsumoAPI.registrarConsumo(date.getTime(), "Insert Venda", "Erro na resposta: " + t.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

            public void gerarItemVenda(Venda vendaResposta, java.sql.Date date) {

                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setVenda(vendaResposta);

                for (int i = 0; i < produtos.size(); i++) {
                    itemVenda.setProduto(produtos.get(i));
                    itemVenda.setQuantidade(Integer.parseInt(tbProdutos.getValueAt(i, 4).toString()));
                    itemVenda.setValorUnitario(produtos.get(i).getPreco());
                    itemVenda.setValorTotal(Double.parseDouble(tbProdutos.getValueAt(i, 5).toString()));

                    Call<ItemVenda> call2 = itemVendaAPI.insert(itemVenda);

                    call2.clone().enqueue(new Callback<ItemVenda>() {
                        @Override
                        public void onResponse(Call<ItemVenda> call, Response<ItemVenda> response) {
                            if (response.isSuccessful()) {
                                try {
                                    logConsumoAPI.registrarConsumo(date.getTime(), "Insert ItemVenda", "Sucesso");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ItemVenda> call, Throwable t) {
                            try {
                                logConsumoAPI.registrarConsumo(date.getTime(), "Insert ItemVenda", "Erro na resposta: " + t.toString());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                }

                defaultTableModel.setRowCount(0);
                txtNomeCliente.setText("");
                txtCodCliente.setText("");
                txtCod.setText("");
                txtDescricao.setText("");
                txtObservacao.setText("");
                spinnerQtd.setValue(1);
                clientePassado.resetCliente();
                produtoPassado.resetProduto();
                atualizarPrecoTotal();

            }
        }).start();

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendaView().setVisible(true);
            }
        });
    }

    //Vai passar a Data para String
    public static String dataParaString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    //Vai passar o String para data
    public static Date stringParaData(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddProduto;
    private javax.swing.JButton btFinalizaVenda;
    private javax.swing.JButton btLimpaVenda;
    private javax.swing.JButton btRemoverProduto;
    private javax.swing.JButton btSelecionaCliente;
    private javax.swing.JButton btSelecionaProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spinnerQtd;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNomeCliente;
    private java.awt.TextField txtObservacao;
    private javax.swing.JTextField txtTotalVenda;
    // End of variables declaration//GEN-END:variables
}
