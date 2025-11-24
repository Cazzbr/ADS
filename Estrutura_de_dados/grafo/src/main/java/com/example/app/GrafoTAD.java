package com.example.app;

import java.util.LinkedList;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GrafoTAD {

    private LinkedList<Integer>[] adj;
    private boolean dirigido;
    private int numVertices;

    public GrafoTAD(int numVertices, boolean dirigido) {
        this.numVertices = numVertices;
        this.dirigido = dirigido;

        this.adj = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void insereAresta(int vA, int vB) {
        adj[vA].add(vB);
        if (!dirigido) {
            adj[vB].add(vA);
        }
    }

    public void imprime() {
        for (int i = 0; i < this.numVertices; i++) {
            System.out.println(i + " : " + adj[i]);
        }
    }

    public void visualizar() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph g = new SingleGraph("Vistualiza");
        for (int i = 0; i < numVertices; i++) {
            g
                .addNode(String.valueOf(i))
                .setAttribute("ui.label", String.valueOf(i));
        }
        int edgeCount = 0;
        int vertCount = 0;
        if (!dirigido) {
            for (LinkedList<Integer> vizinhos : adj) {
                for (int vizinho : vizinhos) {
                    if (vertCount < vizinho) {
                        g.addEdge(
                            "e" + edgeCount++,
                            String.valueOf(vertCount),
                            String.valueOf(vizinho),
                            false
                        );
                    }
                }
                vertCount++;
            }
        } else {
            for (LinkedList<Integer> vizinhos : adj) {
                for (int v : vizinhos) {
                    g.addEdge(
                        "e" + edgeCount++,
                        String.valueOf(vertCount),
                        String.valueOf(v),
                        true
                    );
                }
                vertCount++;
            }
        }
        g.setAttribute(
            "ui.stylesheet",
            "node { size: 40px; fill-color: #f7b731; text-size: 14; }" +
                "edge { fill-color: gray; }"
        );
        g.display();
    }
}
