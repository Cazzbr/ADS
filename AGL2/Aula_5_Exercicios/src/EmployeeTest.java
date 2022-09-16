import java.text.DecimalFormat;

public class EmployeeTest {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) throws Exception {
        
        Employee e1 = new Employee("Carlos", "Amaral", 5350);
        Employee e2 = new Employee("Amanda", "Fava", 6300);
        
        printSalarioAnual(e1);
        printSalarioAnual(e2);
        e1.raiseSalaryByPercent(10);
        e2.raiseSalaryByPercent(10);
        printSalarioAnual(e1);
        printSalarioAnual(e2);
    }

    public static void printSalarioAnual(Employee emp) {
        System.out.println("O Salário anual de " + emp.getNome() + 
        " " + emp.getSobrenome() + "é: " + df.format(emp.calcularSalarioAnual()));
    }
}