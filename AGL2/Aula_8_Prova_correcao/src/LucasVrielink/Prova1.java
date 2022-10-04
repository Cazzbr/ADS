package LucasVrielink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Prova1 {//4,0/5,0 (faltou modularizar o código)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Digite a quantidade de alunos na turma:");
		int quantidadeTurma = Integer.parseInt(br.readLine());
		
		while(quantidadeTurma<0 || quantidadeTurma>50) {
			System.out.println("Digite um numero valido:");
			quantidadeTurma = Integer.parseInt(br.readLine());
		}
		
		
		ArrayList<String> alunosOriginal = new ArrayList<String>();
		for(int i = 0; i<quantidadeTurma; i++) {
			System.out.println("Digite:");
			String guarda = br.readLine();
			alunosOriginal.add(guarda);
		}
		
		
		System.out.println("Digite a quantidade de alunos presentes:");
		int quantidadePresente = Integer.parseInt(br.readLine());
		
		while(quantidadePresente<0 || quantidadePresente> quantidadeTurma) {
			System.out.println("Digite um numero valido:");
			quantidadePresente = Integer.parseInt(br.readLine());
		}
		
		ArrayList<String> alunosPresentes = new ArrayList<String>();
		
		for(int i=0; i<quantidadePresente; i++) {
			System.out.println("Digite o nome e a assinatura da aula:");
			String guarda = br.readLine();
			alunosPresentes.add(guarda);
		}
		
		int contadorFalso=0;
		for(int i=0; i<quantidadePresente; i++) {
			for(int j = 0; j<alunosOriginal.size(); j++) {
				if(alunosPresentes.get(i).equals(alunosOriginal.get(j))) {
					contadorFalso++;
				}
			}
		}
		
		System.out.println("A quantidade de assinaturas falsas são de:" + contadorFalso);
		
	}

}
