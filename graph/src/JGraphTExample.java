// 04) Ejemplo usando JGraphT - www.jgrapht.org
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.List;

public class JGraphTExample {
    public static void main(String[] args) {
        // Crear un grafo no dirigido con pesos
        Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Añadir vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Añadir aristas con pesos
        graph.setEdgeWeight(graph.addEdge("A", "B"), 2);
        graph.setEdgeWeight(graph.addEdge("A", "C"), 5);
        graph.setEdgeWeight(graph.addEdge("B", "C"), 1);
        graph.setEdgeWeight(graph.addEdge("C", "D"), 3);

        System.out.println("Vertices: " + graph.vertexSet());
        System.out.println("Aristas: " + graph.edgeSet());

        // Usar algoritmo de Dijkstra para encontrar el camino más corto de A a D
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        List<String> path = dijkstra.getPath("A", "D").getVertexList();
        double distance = dijkstra.getPathWeight("A", "D");

        System.out.println("Camino más corto de A a D: " + path);
        System.out.println("Distancia total: " + distance);
    }
}
