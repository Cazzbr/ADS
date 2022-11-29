package com.example;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogHandler {
    
    private static final String logFolder = "Logs/";

    public void registerLog(Exception exception){
        try {
            writeLogToFile(exception);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeLogToFile(Exception exception) throws FileNotFoundException{
        String fileName = logFolder + java.time.LocalDateTime.now();
        PrintWriter logFile = new PrintWriter(fileName);

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));

        logFile.println("Log de Erro - SuperMercado!");
        logFile.println("Data: " + java.time.LocalDate.now());
        logFile.println("Hora: " + java.time.LocalTime.now());
        logFile.println("------------------------- Inicio do log -------------------------");
        logFile.println(sw.toString());
        logFile.println("-------------------------- Fim do log -------------------------");
        logFile.close();
    }
}
