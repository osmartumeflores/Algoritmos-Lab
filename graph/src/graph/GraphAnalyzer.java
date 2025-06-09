// 05 y 09) Análisis de tipos de grafos usando GraphLink
package graph;

import java.util.*;

public class GraphAnalyzer {

    // 05.a) Grado de un nodo: número de aristas conectadas
    public static <E> int grado(GraphLink<E> graph, E v) {
        Vertex<E> vertex = graph.searchVertex(v);
        return (vertex != null) ? vertex.listAdj.size() : -1;
    }

    // 05.b) ¿Es camino? Todos los vértices con grado 2, excepto dos con grado 1
    public static <E> boolean esCamino(GraphLink<E> graph) {
        int grado1 = 0, grado2 = 0;
        for (Vertex<E> v : graph.listVertex) {
            int g = v.listAdj.size();
            if (g == 1) grado1++;
            else if (g == 2) grado2++;
            else return false;
        }
        return grado1 == 2 && (grado1 + grado2 == graph.listVertex.size());
    }

    // 05.c) ¿Es ciclo? Todos los vértices tienen grado 2
    public static <E> boolean esCiclo(GraphLink<E> graph) {
        for (Vertex<E> v : graph.listVertex) {
            if (v.listAdj.size() != 2) return false;
        }
        return true;
    }

    // 05.d) ¿Es rueda? Un nodo conectado con todos los demás (grado n-1) y el resto forma ciclo
    public static <E> boolean esRueda(GraphLink<E> graph) {
        int n = graph.listVertex.size();
        int centros = 0;
        for (Vertex<E> v : graph.listVertex) {
            int g = v.listAdj.size();
            if (g == n - 1) centros++;
            else if (g != 2) return false;
        }
        return centros == 1;
    }

    // 05.e) ¿Es completo? Todos los nodos conectados entre sí (grado = n-1)
    public static <E> boolean esCompleto(GraphLink<E> graph) {
        int n = graph.listVertex.size();
        for (Vertex<E> v : graph.listVertex) {
            if (v.listAdj.size() != n - 1) return false;
        }
        return true;
    }

    // 09.a) ¿Es isomorfo? Compara dos grafos por cantidad de nodos, aristas y grados
    public static <E> boolean esIsomorfo(GraphLink<E> g1, GraphLink<E> g2) {
        if (g1.listVertex.size() != g2.listVertex.size()) return false;

        ArrayList<Integer> gradosG1 = new ArrayList<>();
        ArrayList<Integer> gradosG2 = new ArrayList<>();

        for (Vertex<E> v : g1.listVertex) gradosG1.add(v.listAdj.size());
        for (Vertex<E> v : g2.listVertex) gradosG2.add(v.listAdj.size());

        Collections.sort(gradosG1);
        Collections.sort(gradosG2);

        return gradosG1.equals(gradosG2);
    }

    // 09.b) ¿Es plano? Un grafo plano cumple: E ≤ 3V - 6 (solo válido si es conexo y simple)
    public static <E> boolean esPlano(GraphLink<E> graph) {
        int V = graph.listVertex.size();
        int E = contarAristas(graph) / 2;
        return E <= (3 * V - 6);
    }

    // 09.c) ¿Es conexo? Reutiliza isConexo de GraphLink
    public static <E> boolean esConexo(GraphLink<E> graph) {
        return graph.isConexo();
    }

    // 09.d) ¿Es autocomplementario? El grafo complementario es isomorfo al original
    public static <E> boolean esAutoComplementario(GraphLink<E> graph) {
        GraphLink<E> complement = new GraphLink<>();
        for (Vertex<E> v : graph.listVertex) {
            complement.insertVertex(v.getData());
        }
        for (Vertex<E> v1 : graph.listVertex) {
            for (Vertex<E> v2 : graph.listVertex) {
                if (!v1.equals(v2) && !graph.searchEdge(v1.getData(), v2.getData())) {
                    complement.insertEdge(v1.getData(), v2.getData());
                }
            }
        }
        return esIsomorfo(graph, complement);
    }

    private static <E> int contarAristas(GraphLink<E> graph) {
        int total = 0;
        for (Vertex<E> v : graph.listVertex) total += v.listAdj.size();
        return total;
    }
} 
