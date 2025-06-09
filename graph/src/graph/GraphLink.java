// GraphLink.java completamente comentado y corregido con las actividades del Lab 11
package graph;
import lista.*;
import java.util.*;

public class GraphLink<E> {
    // Lista de todos los vértices en el grafo
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    // Actividad 2 - Inserta un vértice si no existe
    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        if (searchVertex(data) == null) {
            listVertex.addLast(newVertex);
        } else {
            System.out.println("El vértice " + data + " ya existe.");
        }
    }

    // Actividad 2 - Inserta una arista no dirigida entre dos vértices
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> origin = searchVertex(verOri);
        Vertex<E> destination = searchVertex(verDes);

        if (origin != null && destination != null) {
            if (!searchEdge(verOri, verDes)) {
                origin.listAdj.addLast(new Edge<>(destination));
                destination.listAdj.addLast(new Edge<>(origin));
            } else {
                System.out.println("La arista entre " + verOri + " y " + verDes + " ya existe.");
            }
        } else {
            System.out.println("Uno o ambos vértices no encontrados para la inserción de arista: " + verOri + ", " + verDes);
        }
    }

    // 01.a) searchVertex(v): busca si existe el vértice 'v'
    public Vertex<E> searchVertex(E v) {
        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                return vertex;
            }
        }
        return null;
    }

    // 01.b) searchEdge(v, z): busca si existe una arista entre 'v' y 'z'
    public boolean searchEdge(E v, E z) {
        Vertex<E> origin = searchVertex(v);
        Vertex<E> destination = searchVertex(z);

        if (origin != null && destination != null) {
            for (Edge<E> edge : origin.listAdj) {
                if (edge.getRefDest().equals(destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 02.a) removeVertex(v): elimina el vértice 'v' y sus aristas
    public void removeVertex(E v) {
        Vertex<E> vertexToRemove = searchVertex(v);
        if (vertexToRemove == null) {
            System.out.println("El vértice " + v + " no fue encontrado.");
            return;
        }
        for (Vertex<E> vertex : listVertex) {
            vertex.listAdj.remove(new Edge<>(vertexToRemove));
        }
        listVertex.remove(vertexToRemove);
        System.out.println("El vértice " + v + " y sus aristas asociadas fueron eliminados exitosamente.");
    }

    // 02.b) removeEdge(v, z): elimina la arista entre 'v' y 'z'
    public void removeEdge(E v, E z) {
        Vertex<E> origin = searchVertex(v);
        Vertex<E> destination = searchVertex(z);

        if (origin != null && destination != null) {
            origin.listAdj.remove(new Edge<>(destination));
            destination.listAdj.remove(new Edge<>(origin));
            System.out.println("La arista entre " + v + " y " + z + " fue eliminada exitosamente.");
        } else {
            System.out.println("Uno o ambos vértices no fueron encontrados para la eliminación de arista: " + v + ", " + z);
        }
    }

    // 02.c) dfs(v): recorrido en profundidad desde 'v'
    public void dfs(E v) {
        Vertex<E> startVertex = searchVertex(v);
        if (startVertex == null) {
            System.out.println("Vértice de inicio " + v + " no encontrado para DFS.");
            return;
        }
        System.out.print("DFS desde " + v + ": ");
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursive(Vertex<E> currentVertex, ListLinked<Vertex<E>> visited) {
        visited.addLast(currentVertex);
        System.out.print(currentVertex.getData() + " ");
        for (Edge<E> edge : currentVertex.listAdj) {
            Vertex<E> neighbor = edge.getRefDest();
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    // 01.a) bfs(v): recorrido en anchura desde 'v'
    public void bfs(E v) {
        Vertex<E> startVertex = searchVertex(v);
        if (startVertex == null) {
            System.out.println("Vértice de inicio " + v + " no encontrado para BFS.");
            return;
        }
        System.out.print("BFS desde " + v + ": ");
        Queue<Vertex<E>> queue = new LinkedList<>();
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        queue.add(startVertex);
        visited.addLast(startVertex);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");
            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.addLast(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // 01.b) bfsPath(v, z): devuelve el camino desde 'v' a 'z' en ArrayList
    public ArrayList<E> bfsPath(E v, E z) {
        Vertex<E> start = searchVertex(v);
        Vertex<E> end = searchVertex(z);
        ArrayList<E> path = new ArrayList<>();
        if (start == null || end == null) return path;

        Map<Vertex<E>, Vertex<E>> parentMap = new HashMap<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        ListLinked<Vertex<E>> visited = new ListLinked<>();

        visited.addLast(start);
        queue.offer(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(end)) break;
            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.addLast(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
        if (!parentMap.containsKey(end)) return path;

        Vertex<E> step = end;
        while (step != null) {
            path.add(0, step.getData());
            step = parentMap.get(step);
        }
        return path;
    }

    // 02.a) insertEdgeWeight(v, z, w): inserta arista ponderada
    public void insertEdgeWeight(E vOrig, E vDest, int w) {
        Vertex<E> vo = searchVertex(vOrig);
        Vertex<E> vd = searchVertex(vDest);
        if (vo != null && vd != null) {
            if (!vo.listAdj.contains(new Edge<>(vd))) {
                vo.listAdj.addLast(new Edge<>(vd, w));
            }
            if (!vd.listAdj.contains(new Edge<>(vo))) {
                vd.listAdj.addLast(new Edge<>(vo, w));
            }
        }
    }

    // 02.b) shortPath(v, z): ruta más corta ponderada usando Dijkstra con ArrayList
    public ArrayList<E> shortPath(E vOrig, E vDest) {
        Vertex<E> start = searchVertex(vOrig);
        Vertex<E> end = searchVertex(vDest);
        ArrayList<E> result = new ArrayList<>();
        if (start == null || end == null) return result;

        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        ListLinked<Vertex<E>> unvisited = new ListLinked<>();

        for (Vertex<E> v : listVertex) {
            dist.put(v, Integer.MAX_VALUE);
            unvisited.addLast(v);
        }
        dist.put(start, 0);

        while (!unvisited.isEmpty()) {
            Vertex<E> current = null;
            int min = Integer.MAX_VALUE;
            for (Vertex<E> v : unvisited) {
                if (dist.get(v) < min) {
                    min = dist.get(v);
                    current = v;
                }
            }
            if (current == null) break;
            unvisited.remove(current);

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.getRefDest();
                int weight = edge.getWeight() > 0 ? edge.getWeight() : 1;
                int newDist = dist.get(current) + weight;
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                }
            }
        }

        Vertex<E> step = end;
        while (step != null) {
            result.add(0, step.getData());
            step = prev.get(step);
        }
        return result;
    }

    // 02.c) isConexo(): verifica si el grafo es conexo
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        dfsRecursive(listVertex.get(0), visited);
        return visited.size() == listVertex.size();
    }

    // 02.d) dijkstra(v, w): devuelve Stack con ruta más corta ponderada
    public Stack<E> dijkstra(E vOrig, E vDest) {
        Vertex<E> start = searchVertex(vOrig);
        Vertex<E> end = searchVertex(vDest);
        Stack<E> result = new Stack<>();
        if (start == null || end == null) return result;

        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        ListLinked<Vertex<E>> unvisited = new ListLinked<>();

        for (Vertex<E> v : listVertex) {
            dist.put(v, Integer.MAX_VALUE);
            unvisited.addLast(v);
        }
        dist.put(start, 0);

        while (!unvisited.isEmpty()) {
            Vertex<E> current = null;
            int min = Integer.MAX_VALUE;
            for (Vertex<E> v : unvisited) {
                if (dist.get(v) < min) {
                    min = dist.get(v);
                    current = v;
                }
            }
            if (current == null) break;
            unvisited.remove(current);

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.getRefDest();
                int weight = edge.getWeight() > 0 ? edge.getWeight() : 1;
                int newDist = dist.get(current) + weight;
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                }
            }
        }

        Vertex<E> step = end;
        while (step != null) {
            result.push(step.getData());
            step = prev.get(step);
        }
        Stack<E> finalResult = new Stack<>();
        while (!result.isEmpty()) finalResult.push(result.pop());
        return finalResult;
    }

    // Muestra la lista de adyacencia del grafo
    public String toString() {
        return listVertex.toString();
    }
}
