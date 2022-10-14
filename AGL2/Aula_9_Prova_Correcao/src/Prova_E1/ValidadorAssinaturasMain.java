package Prova_E1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidadorAssinaturasMain {

    private static BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {

        int tamnhoDaTurma = getInteger();
        ValidadorAssinaturas myval = new ValidadorAssinaturas(tamnhoDaTurma);
        myval.setAssinaturas_originais(lerAssinaturas(tamnhoDaTurma));

        int quantidadeAlunosPresentes;
        do{
            quantidadeAlunosPresentes = getInteger();
            int assinaturasFake = 0;
            for (int i = 0; i < quantidadeAlunosPresentes; i++) {
                Assinatura a = new Assinatura(bf.readLine().split(" "));
                if (!myval.validarAssinatura(a)){
                    assinaturasFake ++;
                }
            }
            System.out.println("O número de assinatutas falsas é: " + assinaturasFake);
        }while(quantidadeAlunosPresentes > 0);
    }

    private static Assinatura[] lerAssinaturas(int tamnhoDaTurma) throws IOException {
        Assinatura[] assinaturas = new Assinatura[tamnhoDaTurma];
        for (int i = 0; i < tamnhoDaTurma; i++) {
            assinaturas[i] = new Assinatura(bf.readLine().split(" "));
        }
        return assinaturas;
    }

    private static int getInteger() throws IOException{
        return Integer.parseInt(bf.readLine());
    }
}
