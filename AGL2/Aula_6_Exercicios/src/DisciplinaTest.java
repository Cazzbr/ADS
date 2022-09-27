public class DisciplinaTest {
    public static void main(String[] args) {
        Disciplina disciplinas_luciano[] = new Disciplina[3];

        Professor rafael = new Professor("Rafael Coelho", " Professor ADS.050480", 399);
        Professor cleber = new Professor("Cleber Macieski", "Professor ADS.050482  e ADS.050481",388);
        
        Disciplina alg1 = new Disciplina("Algoritimos de programação I", rafael, 66);

        disciplinas_luciano[0] = new Disciplina("Algoritimos de programação II", rafael, 66, alg1);
        disciplinas_luciano[1] = new Disciplina("Banco de dados I", cleber, 66);
        disciplinas_luciano[2] = new Disciplina("Paradigmas de Linguagem de Programação", cleber, 66);

        for (Disciplina dis : disciplinas_luciano){
            System.out.println();
            System.out.println(dis);
            System.out.println();
        }
    }
}
