public class InvoiceTest {
    public static void main(String[] args) throws Exception {
        
        Invoice invoice = new Invoice("43652", "Parafuso Sextavado M6x30", 100, 6.30);

        System.out.println("Invoice amount para "+ invoice.getDescricao() + "código:" + invoice.getNumero_peca());
        System.out.println(invoice.getInvoiceAmount());

        System.out.println("Alterado preço para 10.40 e quantidade para 200");
        invoice.setPreco(10.40);
        invoice.setQuantidade(200);

        System.out.println("Alterado preço para 10.40 e quantidade para 200. Novo invoice amount: ");
        System.out.println(invoice.getInvoiceAmount());
    }
}
