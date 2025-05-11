package actividad3;
import actividad1.ExceptionIsEmpty;
public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    class EntryNode {
        E data;
        N priority;
        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
        public String toString() {
            return data + "(" + priority + ")";
        }
    }
    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr) {
        EntryNode entry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(entry);

        if (isEmpty()) {
            first = last = newNode;
            return;
        }

        Node<EntryNode> current = first;
        Node<EntryNode> previous = null;

        while (current != null && current.getData().priority.compareTo(pr) >= 0) {
            previous = current;
            current = current.getNext();
        }

        if (previous == null) {
            newNode.setNext(first);
            first = newNode;
        } else {
            newNode.setNext(current);
            previous.setNext(newNode);
        }

        if (newNode.getNext() == null) last = newNode;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        E data = first.getData().data;
        first = first.getNext();
        if (first == null) last = null;
        return data;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return first.getData().data;
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return last.getData().data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Node<EntryNode> aux = first;
        while (aux != null) {
            sb.append(aux.getData()).append(" ");
            aux = aux.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
