import java.util.List;
import java.util.ArrayList;

public class FuncionesAuxiliares {

    public static <T> boolean buscarElemento(List<T> lista, T valor) {
        return lista.contains(valor);
    }

    public static <T> List<T> invertirLista(List<T> lista) {
        List<T> invertida = new ArrayList<>();
        for (int i = lista.size() - 1; i >= 0; i--) {
            invertida.add(lista.get(i));
        }
        return invertida;
    }

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

    public static <T> int contarNodos(Node<T> head) {
        int count = 0;
        Node<T> actual = head;
        while (actual != null) {
            count++;
            actual = actual.getNext();
        }
        return count;
    }

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

    public static <T> Node<T> concatenarListas(Node<T> l1, Node<T> l2) {
        if (l1 == null) return l2;
        Node<T> current = l1;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(l2);
        return l1;
    }
}
