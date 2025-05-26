package Actividad;
public class NodeAVL<E> extends Node<E> {
    public int bf;

    public NodeAVL(E data) {
        super(data);
        this.bf = 0;
    }

    @Override
    public String toString() {
        return data + "(bf=" + bf + ")";
    }
}
