package com.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.ObservableList;

public class ImpresaoNotaFiscal {
    private Document document;
    private ObservableList<Produto> nfItens;
    private float valorTotalNota;


    public ImpresaoNotaFiscal(ObservableList<Produto> items, float valorTotalNota) {
        this.nfItens = items;
        this.valorTotalNota = valorTotalNota;
    }

    public void printNf() throws ClassNotFoundException, IOException {
        document = new Document();
        String nomeNf = "Notas/" + java.time.LocalDateTime.now();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nomeNf));
            document.open();
            fillEnvoice();
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e){
            App.getInstance().registerLogError(e);
        } 
        App.getInstance().openDocument(nomeNf);
    }

    private void fillEnvoice() throws IOException, DocumentException{
        document.add(new Paragraph("Nota fiscal de venda"));
        List orderedList = new List(List.ORDERED);
        for (Produto produto : nfItens) {
            orderedList.add(new ListItem(produto.toString()));            
        }
        document.add(orderedList);
        document.add(new Paragraph("Valor total da nota: R$ " + this.valorTotalNota));

    }
}
