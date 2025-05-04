import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas<T> {
    private Node<T> head;
    private List<T> tareasCompletadas;

    public GestorDeTareas() {
        this.head = null;
        this.tareasCompletadas = new ArrayList<>();
    }

    public void agregarTarea(T tarea) {
        Node<T> newNode = new Node<>(tarea);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean eliminarTarea(T tarea) {
        if (head == null) return false;

        if (head.getData().equals(tarea)) {
            head = head.getNext();
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(tarea)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean contieneTarea(T tarea) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(tarea)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void imprimirTareas() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public int contarTareas() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public T obtenerTareaMasPrioritaria() {
        if (head == null) return null;

        Node<T> current = head;
        Tarea max = null;

        while (current != null) {
            if (current.getData() instanceof Tarea) {
                Tarea actual = (Tarea) current.getData();
                if (max == null || actual.getPrioridad() > max.getPrioridad()) {
                    max = actual;
                }
            }
            current = current.getNext();
        }
        return (T) max;
    }

    public void invertirTareas() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void completarTarea(T tarea) {
        if (eliminarTarea(tarea)) {
            tareasCompletadas.add(tarea);
        }
    }

    public void imprimirTareasCompletadas() {
        System.out.println("Tareas completadas:");
        for (T tarea : tareasCompletadas) {
            System.out.println(tarea);
        }
    }
}
