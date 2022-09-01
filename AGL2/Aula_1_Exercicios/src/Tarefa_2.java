/*Faça um programa que leia o nome de um vendedor, o seu salário fixo e o
total de vendas efetuadas por ele no mês (em dinheiro). Sabendo que este
vendedor ganha 15% de comissão sobre suas vendas efetuadas, informar
o total a receber no final do mês, com duas casas decimais
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Tarefa_2 {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // Var para o nome do vendedor
        String nome_vendedor;
        // Vars para salario fixo do vendedor e total de vendas no mes
        double salario_fixo, total_vendas, salario_total;
        // Msg na tela para nome do vendedor
        System.out.println("Digite o nome do Vendedor: ");
        // Recebe nome do vendedor na variavel nome_vendedor
        nome_vendedor = bf.readLine();
        // Msg na tela para salário do vendedor
        System.out.println("Digite salário fixo do vendedor: ");
        // Recebe salario fixo do vendedor na variavel salario_fixo convertendo pra double
        salario_fixo = Double.parseDouble(bf.readLine());
        // Msg na tela para total de vendas mensal do vendedor
        System.out.println("Digite o total de vendas do vendedor: ");
        // Recebe o total de vendas do vendedor na variavel total_vendas convertendo pra double
        total_vendas = Double.parseDouble(bf.readLine());
        // Calculo do salario total do vendedor
        salario_total = salario_fixo + (total_vendas * 0.15);
        // Informa o salario do vendedor + comissão no mês
        System.out.println("A remuneração de " + nome_vendedor + " este mês é: " + df.format(salario_total));
    }
}
