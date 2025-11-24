package com.example.app;

public class Main {

    public static void main(String[] args) {
        GrafoTAD grafo = new GrafoTAD(6, false);

        grafo.insereAresta(0, 2);
        grafo.insereAresta(1, 2);
        grafo.insereAresta(1, 4);
        grafo.insereAresta(2, 3);
        grafo.insereAresta(2, 4);
        grafo.insereAresta(3, 5);
        grafo.insereAresta(4, 5);

        grafo.imprime();

        grafo.visualizar();
    }
}
