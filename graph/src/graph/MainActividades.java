// MainActividades.java - Prueba de actividades base del Lab 11
package graph;

public class MainActividades {
    public static void main(String[] args) {
        // Punto 2 - GraphLink (no dirigido)
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        System.out.println("\nGrafo no dirigido (GraphLink):\n" + g);
        g.dfs("A");
        g.bfs("A");

        // BFS Path y Dijkstra
        System.out.println("BFS Path de A a D: " + g.bfsPath("A", "D"));
        g.insertEdgeWeight("C", "D", 2);
        System.out.println("Ruta más corta A->D: " + g.shortPath("A", "D"));

        // Representación del grafo
        GraphRepresentation.representacionFormal(g);
        GraphRepresentation.listaAdyacencia(g);
        GraphRepresentation.matrizAdyacencia(g);

        // Punto 3 - GraphListEdge
        GraphListEdge<String, String> edgeGraph = new GraphListEdge<>();
        edgeGraph.insertVertex("X");
        edgeGraph.insertVertex("Y");
        edgeGraph.insertVertex("Z");

        edgeGraph.insertEdge("X", "Y");
        edgeGraph.insertEdge("Y", "Z");

        System.out.println("\nGrafo representado por lista de aristas (GraphListEdge):\n" + edgeGraph);
        edgeGraph.bfs("X");
    }
}
