package exercicio_1_carro;

import java.util.Calendar;
import java.util.Date;

public class Carro {
    public String marca;
    public String cor;
    public String placa;
    public Integer velocidadeAtual;
    public char marchaAtual;
    public Boolean freioDeMaoPuxado;
    public Boolean chaveVirada;

    public int anoDeFabricacao;
    public int tamanhoPortaMalasLitros;
    public boolean tetoSolar;
    public boolean _farolBaixo = false;
    public boolean _farolAlto = false;
    public boolean _farolNeblina = false;
    public boolean piscaAlertaDireto = false;
    public boolean piscaAlertaEsquerdo = false;
    public Date proximaTrocaOleo;
    public String estadoPneus;

    public void ligarFarol(){
        _farolBaixo = true;
    }

    public void desligarFarol(){
        _farolAlto = false;
        _farolBaixo = false;
        _farolNeblina = false;
    }

    public void alternarFarolBaixoAlto(){
        if (_farolBaixo){
            _farolBaixo = false;
            _farolAlto = true;
        }else{
            _farolBaixo = true;
            _farolAlto = false;
        }
    }
    
    public void precionarChaveFarolNeblina(){
        if (_farolNeblina){
            _farolNeblina = false;
        }else{
            _farolNeblina = true;
        }
    }
    
    public boolean DeveTrocarOleo(){
        if (proximaTrocaOleo != null){
            Date today = Calendar.getInstance().getTime();
            if (proximaTrocaOleo.before(today)){
                return true;
            }
        }
        return false;
    }

    public void acionarAlerta(){
        piscaAlertaDireto = true;
        piscaAlertaEsquerdo = true;
    }

    public void desligarAlerta(){
        piscaAlertaDireto = false;
        piscaAlertaEsquerdo = false;
    }

    public void acionarPiscaAltertaDireta(){
        piscaAlertaDireto = true;
    }

    public void desligarPiscaAlertaDireita(){
        piscaAlertaDireto = false;
    }

    public void acionarPiscaAlertaEsquerda(){
        piscaAlertaEsquerdo = true;
    }

    public void desligarPiscaAlertaEsquerda(){
        piscaAlertaEsquerdo = false;
    }

    public void ligar() {
        chaveVirada = true;
        freioDeMaoPuxado = false;
        marchaAtual = '1';
    }

    public void acelerarAte(Integer velocidade) {
        if (!chaveVirada) return;
        if (freioDeMaoPuxado) return;
        if (marchaAtual == '0') return;
        velocidadeAtual = velocidade;
    }

    public void mudarMarcha(char marcha) {
        marchaAtual = marcha;
    }

    public void parar() {
        acelerarAte(0);
        freioDeMaoPuxado = true;
        chaveVirada = false;
        marchaAtual = '1';
    }

    public String toString() {
        String out = "";
        out += "Marca(" + marca + ") ";
        out += "Cor(" + cor + ") ";
        out += "Placa(" + placa + ") ";
        out += "Chave(" + chaveVirada + ") ";
        out += "Freio(" + freioDeMaoPuxado + ") ";
        out += "Marcha(" + marchaAtual + ") ";
        out += "Ano(" + anoDeFabricacao + ")";
        out += "Tam. Porta Malas(" + tamanhoPortaMalasLitros + ")";
        out += "Teto(" + tetoSolar + ")";
        out += "Farol Baixo(" + _farolBaixo + ")";
        out += "Farol Alto(" + _farolAlto + ")";
        out += "Farol Neblina(" + _farolNeblina + ")";
        out += "Pisca Dir.(" + piscaAlertaDireto + ")";
        out += "Pisca Esq.(" + piscaAlertaEsquerdo + ")";
        out += "Prox. Troca Óleo(" + proximaTrocaOleo + ")";
        out += "Est. Pneus(" + estadoPneus + ")";
        return out;
    }


}