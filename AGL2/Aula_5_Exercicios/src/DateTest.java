public class DateTest {
    public static void main(String[] args) {
        Date data = new Date(8, 31, 2030);

        System.out.println("A data é: " + data.displayDate());

        System.out.println("Vamos mudar a data? A nova data é: ");
        data.setAno(2030);
        data.setMes(1);
        data.setDia(5);

        System.out.println("A data é: " + data.displayDate());
    }
}
