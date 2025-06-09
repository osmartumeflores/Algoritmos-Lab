import graph.GraphLink;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");

        System.out.println("Grafo:");
        System.out.println(graph);

        graph.dfs("A");
        graph.bfs("A");

        System.out.println("Camino BFS de A a D: " + graph.bfsPath("A", "D"));

        graph.insertEdgeWeight("C", "D", 5);
        System.out.println("Grafo con peso entre C y D:");
        System.out.println(graph);
    }
}
