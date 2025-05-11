package actividad1;
public class StackArray<E> implements Stack<E> {
    private E[] array;
    private int tope;

    public StackArray(int n) {
        this.array = (E[]) new Object[n];
        tope = -1;
    }

    public void push(E x) {
        if (isFull())
            throw new RuntimeException("Pila llena");
        array[++tope] = x;
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Pila vacía");
        return array[tope--];
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Pila vacía");
        return array[tope];
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == array.length - 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = tope; i >= 0; i--) {
            sb.append(array[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
