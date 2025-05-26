package Actividad;

// Clase BSTree sin balanceo para comparación
public class BSTree<E extends Comparable<E>> {
    public Node<E> root;

    public void insert(E value) {
        root = insert(root, value);
    }

    private Node<E> insert(Node<E> node, E value) {
        if (node == null) return new Node<>(value);
        int cmp = value.compareTo(node.data);
        if (cmp < 0) node.left = insert(node.left, value);
        else if (cmp > 0) node.right = insert(node.right, value);
        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node + " ");
            inorder(node.right);
        }
    }
}