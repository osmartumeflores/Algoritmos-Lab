package ejercicio3;

import actividad1.ExceptionIsEmpty;
import actividad2.Queue;
import actividad2.QueueLink;
import actividad3.PriorityQueue;

public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {
    private Queue<E>[] queues;
    private int numPriorities;

    public PriorityQueueLinked(int numPriorities) {
        this.numPriorities = numPriorities;
        queues = new Queue[numPriorities];
        for (int i = 0; i < numPriorities; i++) {
            queues[i] = new QueueLink<>();
        }
    }

    public void enqueue(E x, Integer pr) {
        if (pr < 0 || pr >= numPriorities)
            throw new IllegalArgumentException("Prioridad fuera de rango");
        queues[pr].enqueue(x);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) return queues[i].dequeue();
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public E front() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) return queues[i].front();
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public E back() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) return queues[i].back();
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public boolean isEmpty() {
        for (Queue<E> q : queues) {
            if (!q.isEmpty()) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = numPriorities - 1; i >= 0; i--) {
            sb.append("Prioridad ").append(i).append(": ").append(queues[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
