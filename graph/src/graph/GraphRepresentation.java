// 06 y 08) Representación de grafos con GraphLink
package graph;

public class GraphRepresentation {

    // 06.a) Representación formal: lista de vértices y sus aristas
    public static <E> void representacionFormal(GraphLink<E> graph) {
        System.out.println("\n06.a) Representación Formal:");
        for (Vertex<E> v : graph.listVertex) {
            System.out.print("Vértice " + v.getData() + " conectado a: ");
            for (Edge<E> edge : v.listAdj) {
                System.out.print(edge.getRefDest().getData() + " ");
            }
            System.out.println();
        }
    }

    // 06.b) Lista de adyacencias por vértice
    public static <E> void listaAdyacencia(GraphLink<E> graph) {
        System.out.println("\n06.b) Lista de Adyacencia:");
        for (Vertex<E> v : graph.listVertex) {
            System.out.print(v.getData() + " -> ");
            for (Edge<E> edge : v.listAdj) {
                System.out.print(edge.getRefDest().getData() + " ");
            }
            System.out.println();
        }
    }

    // 06.c) Matriz de adyacencia (0: no conectado, 1: conectado)
    public static <E> void matrizAdyacencia(GraphLink<E> graph) {
        int n = graph.listVertex.size();
        System.out.println("\n06.c) Matriz de Adyacencia:");

        // Cabecera
        System.out.print("    ");
        for (Vertex<E> v : graph.listVertex) {
            System.out.print(v.getData() + " ");
        }
        System.out.println();

        // Cuerpo de la matriz
        for (Vertex<E> vi : graph.listVertex) {
            System.out.print(vi.getData() + " | ");
            for (Vertex<E> vj : graph.listVertex) {
                System.out.print((graph.searchEdge(vi.getData(), vj.getData()) ? "1 " : "0 "));
            }
            System.out.println();
        }
    }
} 
