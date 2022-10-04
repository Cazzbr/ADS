package LucianoMagri;

public class EstacionamentoTest {//5,0/5,0
    private static Estacionamento meu_estacionamento = new Estacionamento("Meu RafaelliSantos.Estacionamento", 5.30);

    public static void main(String[] args) {
        entrada_carro();
        carros_no_estacionamento();
        saida_carro();
        valor_recebido_dia();
        procurar_carro_placa("AAA-3333");

    }

    private static void procurar_carro_placa(String string) {

        System.out.println("RafaelliSantos.Carro com placa " + string + " existe: " + meu_estacionamento.procurar_carro_placa(string));
    }

    public static void entrada_carro(){
        Carro c = new Carro("modelo", "AAA-3333");
        meu_estacionamento.setCarros(c);
    }

    public static void saida_carro(){
        System.out.println("O valor a ser pago é:" + meu_estacionamento.saida_carro(0));
        
    }

    public static void valor_recebido_dia(){
        System.out.println("O valor recebido até agora é: " + meu_estacionamento.getValor_total());
    }

    public static void carros_no_estacionamento(){
        System.out.println(meu_estacionamento.mostrar_carros_estacionados());
    }
}
