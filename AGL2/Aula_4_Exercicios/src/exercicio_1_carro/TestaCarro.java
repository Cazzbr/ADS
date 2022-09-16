package exercicio_1_carro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestaCarro {
    static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String[] args) throws ParseException {
        Carro gol = new Carro();
        Carro uno = new Carro();

        gol.marca = "Wolkswagen";
        gol.cor = "branca";
        gol.placa = "XYZ9803";
        uno.marca = "Fiat";
        uno.cor = "vermelha";
        uno.placa = "ABC4445";
        System.out.println(gol);
        System.out.println(uno);

        gol.ligar();
        gol.acelerarAte(20);
        gol.mudarMarcha('2');
        System.out.println(gol);
        System.out.println(uno);
        uno.ligar();
        gol.acelerarAte(50);
        gol.mudarMarcha('3');
        uno.acelerarAte(10);
        uno.mudarMarcha('2');
        System.out.println(gol);
        System.out.println(uno);

        gol.anoDeFabricacao = 1998;
        gol.tamanhoPortaMalasLitros = 290;
        gol.tetoSolar = false;
        gol.proximaTrocaOleo = df.parse("12/11/2022");
        gol.estadoPneus = "Na capa da gaita";

        uno.anoDeFabricacao = 2004;
        uno.tamanhoPortaMalasLitros = 265;
        uno.tetoSolar = false;
        uno.proximaTrocaOleo = df.parse("10/07/2022");
        uno.estadoPneus = "Novos";
        System.out.println();
        System.out.println(gol);
        System.out.println(uno);
        System.out.println();
        gol.ligarFarol();
        gol.alternarFarolBaixoAlto();
        uno.precionarChaveFarolNeblina();
        gol.acionarAlerta();
        System.out.println();
        System.out.println(gol);
        System.out.println(uno);
        System.out.println();
        uno.acionarPiscaAlertaEsquerda();
        gol.desligarAlerta();

        System.out.println("Devemos de trocar o óleo do Gol? " + gol.DeveTrocarOleo());
        System.out.println("Devemos de trocar o óleo do uno? " + uno.DeveTrocarOleo());
    }
}
