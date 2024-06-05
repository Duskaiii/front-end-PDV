/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unipar.projetointegrado.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dzkyy
 */
public class LogVendas {

    public void regVenda(Long data, String cliente, double valorT) throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataAtual = dateFormat.format(data);

        File log = new File("VendaLogs/LogVenda.txt");
        File dir = new File("VendaLogs");

        dir.mkdirs();

        try {
            if (log.exists() == false) {
                log.createNewFile();
                try (FileWriter escritor = new FileWriter("VendaLogs/LogVenda.txt"); BufferedWriter buff = new BufferedWriter(escritor)) {

                    buff.write("Data: " + dataAtual + "\n");
                    buff.write("Nome:" + cliente + "\n");
                    buff.write("Valor Total:" + valorT + "\n");
                    

                } catch (IOException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else {
                PrintWriter out = new PrintWriter(new FileWriter(log, true));
                out.append("**************************" + "\n" + "Data: " + dataAtual + "\n"
                        + "Cliente: "+cliente + "\nValor Total: "+ valorT + "\n");
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo de registro LOG!!");
        }

    }

}
