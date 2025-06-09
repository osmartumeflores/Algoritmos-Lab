// GraphListEdge.java - Punto 03 del Lab 11
package graph;
import java.util.*;
// Clase que representa un vértice con información y posición
class VertexObj<V,E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position){
        this.info = info;
        this.position = position;
    }

    public V getInfo() { return info; }
    public int getPosition() { return position; }
    public String toString() { return info.toString(); }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj<?,?> vertex) {
            return this.info.equals(vertex.info);
        }
        return false;
    }
}

// Clase que representa una arista entre dos vértices con peso/info
class EdgeObj<V,E> {
    protected E info;
    protected VertexObj<V,E> endVertex1;
    protected VertexObj<V,E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V,E> vert1, VertexObj<V,E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    public VertexObj<V,E> getV1() { return endVertex1; }
    public VertexObj<V,E> getV2() { return endVertex2; }
    public String toString() { return endVertex1 + " -- " + endVertex2 + " [" + info + "]"; }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof EdgeObj<?,?> edge) {
            return (edge.endVertex1.equals(endVertex1) && edge.endVertex2.equals(endVertex2)) ||
                   (edge.endVertex1.equals(endVertex2) && edge.endVertex2.equals(endVertex1));
        }
        return false;
    }
}

// 03) Implementación del grafo usando listas de aristas
public class GraphListEdge<V,E> {
    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge(){
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // 03.a) insertVertex(v): agrega un vértice si no existe
    public void insertVertex(V info){
        if (!searchVertex(info)) {
            secVertex.add(new VertexObj<>(info, secVertex.size()));
        }
    }

    // 03.b) insertEdge(v, z): agrega una arista si no existe
    public void insertEdge(V v1, V v2){
        VertexObj<V,E> vert1 = getVertex(v1);
        VertexObj<V,E> vert2 = getVertex(v2);
        if (vert1 != null && vert2 != null && !searchEdge(v1, v2)) {
            secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
        }
    }

    // 03.c) searchVertex(v): devuelve true si el vértice existe
    public boolean searchVertex(V info){
        return getVertex(info) != null;
    }

    // 03.d) searchEdge(v, z): devuelve true si la arista existe
    public boolean searchEdge(V v1, V v2){
        VertexObj<V,E> vert1 = getVertex(v1);
        VertexObj<V,E> vert2 = getVertex(v2);
        if (vert1 != null && vert2 != null) {
            for (EdgeObj<V,E> e : secEdge) {
                if ((e.getV1().equals(vert1) && e.getV2().equals(vert2)) ||
                    (e.getV1().equals(vert2) && e.getV2().equals(vert1))) return true;
            }
        }
        return false;
    }

    // 03.e) bfs(v): recorrido en anchura desde v
    public void bfs(V v) {
        VertexObj<V,E> start = getVertex(v);
        if (start == null) {
            System.out.println("Vértice " + v + " no encontrado");
            return;
        }

        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS desde " + v + ": ");
        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.print(current + " ");
            for (EdgeObj<V,E> edge : secEdge) {
                VertexObj<V,E> neighbor = null;
                if (edge.getV1().equals(current) && !visited.contains(edge.getV2())) {
                    neighbor = edge.getV2();
                } else if (edge.getV2().equals(current) && !visited.contains(edge.getV1())) {
                    neighbor = edge.getV1();
                }
                if (neighbor != null) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Utilidad: obtener objeto vértice por info
    private VertexObj<V,E> getVertex(V info){
        for (VertexObj<V,E> v : secVertex) {
            if (v.getInfo().equals(info)) return v;
        }
        return null;
    }

    @Override
    public String toString(){
        return "Vértices: " + secVertex + "\nAristas: " + secEdge;
    }
} 
