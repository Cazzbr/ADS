public class CrimeAnalyzer {
    private String perguntas[] = {"Telefonou para a vítima?", "Esteve no local do crime?"
                                , "Mora perto da vítima?", "Devia para a vítima?"
                                , "Já trabalhou com a vítima?"
    };
    
    private int respostas[] = new int[5];
    
    public int getNumeroPerguntas(){
        return this.perguntas.length;
    }

    public String getClassificacao(){
        String classificacao = " *** Inocente ***";
        int number_yes = 0;
        for (int resp : respostas) {
            number_yes += resp;
        }
        if (number_yes == 5){
            classificacao = " *** Assassino ***";
        } else if (number_yes > 2){
            classificacao = " *** Cúmplice ***";
        } else if (number_yes == 2){
            classificacao = " *** Suspeito ***";
        }
        return classificacao;
    }

    public String getPergunta(int numero_pergunta) {
        return perguntas[numero_pergunta];
    }

    public int[] getRespostas() {
        return respostas;
    }
    public void setResposta(int resposta, int numero_pergunta) {
        this.respostas[numero_pergunta] = resposta;
    }
}
