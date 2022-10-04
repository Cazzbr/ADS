package LucianoFerri;

import javax.swing.JOptionPane;

//5,0/5,0
public class Assinatura { //nome da classe não pode ser diferente do nome do arquivo

	public static void main(String[] args) {
		int qt = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de alunos da turma:"));
		String nomeOriginal[] = new String[qt];
		nomeOriginal = pegaOriginais(nomeOriginal, qt);
		int pt = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de alunos presentes no dia:"));
		String ass[] = new String[pt+1];
		ass = pegaAss(ass, pt, nomeOriginal);
		JOptionPane.showMessageDialog(null, "Assinaturas originais:\n"+nomeOriginal.toString()+"\nAssinaturas do dia:\n"+ass.toString()+"\nQuantidade de assinaturas falsas:\n"+ass[pt]);
	}
	
	private static String[] pegaOriginais(String vetor[], int tamanho) {
		
		for(int i=0; i<tamanho; i++) {
			vetor[i]=JOptionPane.showInputDialog("Digite a assinatura original do "+(i+1)+"º aluno:");
		}
		return vetor;
	}
	
	private static String[] pegaAss(String vetor[], int tamanho, String vetor2[]) {
		int cont1 = 0;
		for(int i=0; i<tamanho; i++) {
			vetor[i]=JOptionPane.showInputDialog("Digite a assinatura recebida do "+(i+1)+"º aluno:");
			cont1 = testaAss(vetor[i],vetor2[i],cont1);
		}
		vetor[tamanho]=Integer.toString(cont1);
		return vetor;
	}
	
	private static int testaAss (String vetorOriginais, String vetorTestar, int cont1) { 
		char[] a;
		char[] b;
		int cont = 0;
		for(int i=0; i<vetorTestar.length(); i++) {
			a= vetorTestar.toCharArray();
			b= vetorOriginais.toCharArray();
			
			if(a==b) {
				continue;
			}else {
				cont++;
				if(cont<2) {
					continue;
				}else {
					cont1++;
				}
			}
		}
		return cont1;
	}
	
	
}
