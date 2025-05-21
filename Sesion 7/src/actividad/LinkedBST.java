package actividad;
import actividad.BinarySearchTree;
import actividad.ItemDuplicated;
import actividad.ItemNoFound;
import actividad.ExceptionIsEmpty;
import java.util.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    public class Node {
        public E data;
        public Node left;
        public Node right;
        Node(E data) { this.data = data; }
    }

    private Node root;

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node insert(Node node, E data) throws ItemDuplicated {
        if (node == null) return new Node(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = insert(node.left, data);
        else if (cmp > 0) node.right = insert(node.right, data);
        else throw new ItemDuplicated("Elemento duplicado: " + data);
        return node;
    }

    @Override
    public E search(E data) throws ItemNoFound {
        Node found = search(root, data);
        if (found == null) throw new ItemNoFound("Elemento no encontrado: " + data);
        return found.data;
    }

    private Node search(Node node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return search(node.left, data);
        else if (cmp > 0) return search(node.right, data);
        else return node;
    }

    @Override
    public void delete(E data) throws ItemNoFound, ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol está vacío");
        root = delete(root, data);
    }

    private Node delete(Node node, E data) throws ItemNoFound {
        if (node == null) throw new ItemNoFound("Elemento no encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = delete(node.left, data);
        else if (cmp > 0) node.right = delete(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = findMinNode(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void destroy() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Árbol vacío");
        root = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.data).append(" ");
            inOrder(node.right, sb);
        }
    }

    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString().trim();
    }

    private void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString().trim();
    }

    private void postOrder(Node node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    private Node findMinNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMaxNode(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public E findMin() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("Árbol vacío");
        return findMinNode(root).data;
    }

    public E findMax() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("Árbol vacío");
        return findMaxNode(root).data;
    }
    //metodos para los ejercicios
    public Node getRoot() {
        return root;
    }

    public Node searchNode(E data) {
        return search(root, data);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = 0;

    while (!queue.isEmpty()) {
        int size = queue.size();
        height++;
        for (int i = 0; i < size; i++) {
            Node curr = queue.poll();
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }
    return height;
    }
}