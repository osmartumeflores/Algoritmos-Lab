package Actividad;
public class Node<E> {
    public E data;
    public Node<E> left, right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return data.toString();
    }
}