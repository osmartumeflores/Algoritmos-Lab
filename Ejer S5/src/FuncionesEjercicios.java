import java.util.List;
import java.util.ArrayList;

public class FuncionesEjercicios {

    // 1. Buscar un elemento genérico en una lista
    public static <T> boolean buscarElemento(List<T> lista, T valor) {
        return lista.contains(valor);
    }

    // 2. Invertir una lista genérica
    public static <T> List<T> invertirLista(List<T> lista) {
        List<T> listaInvertida = new ArrayList<>();
        for (int i = lista.size() - 1; i >= 0; i--) {
            listaInvertida.add(lista.get(i));
        }
        return listaInvertida;
    }

    // 3. Insertar un nodo al final
    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (head == null) {
            return nuevo;
        }
        Node<T> actual = head;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(nuevo);
        return head;
    }

    // 4. Contar los nodos
    public static <T> int contarNodos(Node<T> head) {
        int contador = 0;
        Node<T> actual = head;
        while (actual != null) {
            contador++;
            actual = actual.getNext();
        }
        return contador;
    }

    // 5. Comparar dos listas
    public static <T> boolean sonIguales(Node<T> l1, Node<T> l2) {
        Node<T> a = l1;
        Node<T> b = l2;
        while (a != null && b != null) {
            if (!a.getData().equals(b.getData())) {
                return false;
            }
            a = a.getNext();
            b = b.getNext();
        }
        return a == null && b == null;
    }

    // 6. Concatenar dos listas
    public static <T> Node<T> concatenarListas(Node<T> l1, Node<T> l2) {
        if (l1 == null) return l2;
        Node<T> actual = l1;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(l2);
        return l1;
    }
}
