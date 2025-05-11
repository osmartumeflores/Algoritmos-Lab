package ejercicio2;

import actividad1.ExceptionIsEmpty;
import actividad2.Queue;

public class QueueArray<E> implements Queue<E> {
    private E[] data;
    private int first, last, count;

    public QueueArray(int size) {
        this.data = (E[]) new Object[size];
        this.first = 0;
        this.last = -1;
        this.count = 0;
    }

    public void enqueue(E x) {
        if (isFull()) throw new RuntimeException("Cola llena");
        last = (last + 1) % data.length;
        data[last] = x;
        count++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        E value = data[first];
        first = (first + 1) % data.length;
        count--;
        return value;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return data[first];
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return data[last];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == data.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < count; i++) {
            sb.append(data[(first + i) % data.length]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
