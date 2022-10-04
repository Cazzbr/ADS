package VitoriaBomfim;

import java.util.HashMap;
import java.util.Scanner;

public class ListaDePresenca {//5,0/5,0

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        int m;
        HashMap<String, String> nomesAssinaturas = new HashMap<>();
        
        while (true) {
            n = Integer.parseInt(s.nextLine());
            
            if (n == 0) {
                break;
            }
            
            for (int i = 0; i < n; i++) {
                String entrada = s.nextLine();
                String[] nomeEAss = entrada.split(" ");
                String nome = nomeEAss[0];
                String assinatura = nomeEAss[1];
                
                nomesAssinaturas.put(nome, assinatura);
            }
            
            m = Integer.parseInt(s.nextLine());
            
            int assinaturasFalsas = 0;
            
            for (int i = 0; i < m; i++) {
                String entrada = s.nextLine();
                String[] nomeEAss = entrada.split(" ");
                String nome = nomeEAss[0];
                String assinatura = nomeEAss[1];
                
                int diferencas = 0;
                
                String original = nomesAssinaturas.get(nome);
                
                for (int j = 0; j < original.length(); j++) {
                    if (original.charAt(j) != assinatura.charAt(j)) {
                        diferencas++;
                    }
                }
                
                if (diferencas > 1) {
                    assinaturasFalsas++;
                }
            }
            
            System.out.println(assinaturasFalsas);
        }
    }
    
}
