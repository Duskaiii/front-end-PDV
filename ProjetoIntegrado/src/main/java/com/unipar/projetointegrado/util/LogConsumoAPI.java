package com.unipar.projetointegrado.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogConsumoAPI {

    public void registrarConsumo(Long data, String tipoConsumo, String status) throws IOException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataAtual = dateFormat. format(data);

        File log = new File("ApiLogs/logs.txt");
        File dir = new File("ApiLogs");

        dir.mkdirs();

        try{
            if(log.exists()==false){
                log.createNewFile();
                try (FileWriter escritor = new FileWriter("ApiLogs/logs.txt"); BufferedWriter buff = new BufferedWriter(escritor)) {

                    buff.write("Data: " + dataAtual + "\n");
                    buff.write(tipoConsumo + "\n");
                    buff.write("Status: " + status + "\n");


                } catch (IOException e) {
                    System.out.println("erro: " + e.getMessage());
                }
            } else {
                PrintWriter out = new PrintWriter(new FileWriter(log, true));
                out.append("**************************" + "\n" + "Data: " + dataAtual + "\n" +
                        tipoConsumo + "\n" + "Status: " + status + "\n");
                out.close();
            }
        }catch(IOException e){
            System.out.println("Erro ao gerar o arquivo de registro LOG!!");
        }



    }

}
