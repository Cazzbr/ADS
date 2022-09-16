public class Jogador {
    private int id;
    private String nome;
    private String apelido;
    private Date data_nascimento;
    private int numero;
    private String posicao;
    private int qualidade;
    private int cartoes = 0;
    private boolean suspenso = false;

    public Jogador(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Jogador(int id, String nome, String apelido, Date data_nascimento){
        this(id, nome);
        this.apelido = apelido;
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }
    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getPosicao() {
        return posicao;
    }
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getQualidade() {
        return qualidade;
    }
    public void setQualidade(int qualidade) {
        this.qualidade = qualidade;
    }

    public int getCartoes() {
        return cartoes;
    }
    public void setCartoes(int cartoes) {
        this.cartoes = cartoes;
    }

    public boolean aptoParaJogar() {
        return !suspenso;
    }

    public void setSuspenso(boolean suspenso) {
        this.suspenso = suspenso;
    }

    public void aplicarCartao(int quantidade){
        this.cartoes += quantidade;
        if (this.cartoes > 2){
            this.suspenso = true;
        }
    }
    public void cumprirSuspencao(){
        this.cartoes = 0;
        this.suspenso = false;
    }

    public String toString() {
        String status = "Liberado para jogar!";
        if (suspenso){
            status = "suspenso!";
        }
        String out = "-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
        out += "Jogador: " + nome+ "!\n";
        out += "Situação: " + status + "\n";
        return out;
    }

    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Jogador) || x == null){
            return false;
        }
        Jogador j = (Jogador) x;
        if (this.id == j.id && this.id != 0){
           return true;
        }
        if (this.id == 0 && j.id == 0){
            return j.nome.equals(this.nome);
        }
        return false;
    }
}    
