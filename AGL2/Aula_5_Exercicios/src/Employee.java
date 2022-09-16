public class Employee {

    private String nome;
    private String sobrenome;
    private double salario_mensal;

    public Employee(String nome, String sobrenome, double salario_mensal){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.salario_mensal = salario_mensal;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public double getSalario_mensal() {
        return salario_mensal;
    }

    public void setSalario_mensal(double salario_mensal) {
        if (salario_mensal > 0){
            this.salario_mensal = salario_mensal;
        }
    }

    public double calcularSalarioAnual(){
        return (double)(this.salario_mensal * 12);
    }

    public void raiseSalaryByPercent(float amount){
        if (amount > 0){
            this.salario_mensal = this.salario_mensal * (1 + amount/100);
        }
    }
}
