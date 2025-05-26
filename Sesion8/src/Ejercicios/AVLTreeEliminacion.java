package Ejercicios;
import Actividad.AVLTree;
import java.util.*;

public class AVLTreeEliminacion<E extends Comparable<E>> {
    private static class Node<E> {
        E data;
        int bf;
        Node<E> left, right;

        Node(E data) {
            this.data = data;
            this.bf = 0;
        }

        public String toString() {
            return data + "(bf=" + bf + ")";
        }
    }

    private Node<E> root;

    public void insert(E value) {
        root = insert(root, value);
    }

    public void remove(E value) {
        root = remove(root, value);
    }

    private Node<E> insert(Node<E> node, E value) {
        if (node == null) return new Node<>(value);
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, value);
            node.bf--;
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
            node.bf++;
        }
        return balance(node);
    }

    private Node<E> remove(Node<E> node, E value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, value);
            node.bf++;
        } else if (cmp > 0) {
            node.right = remove(node.right, value);
            node.bf--;
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<E> successor = getMin(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data);
        }
        return balance(node);
    }

    private Node<E> getMin(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<E> balance(Node<E> node) {
        if (node == null) return null;
        if (node.bf == 2) {
            if (node.right.bf < 0) node.right = rotateRight(node.right);
            node = rotateLeft(node);
        } else if (node.bf == -2) {
            if (node.left.bf > 0) node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        return node;
    }

    private Node<E> rotateLeft(Node<E> a) {
        Node<E> b = a.right;
        a.right = b.left;
        b.left = a;
        updateBF(a);
        updateBF(b);
        return b;
    }

    private Node<E> rotateRight(Node<E> a) {
        Node<E> b = a.left;
        a.left = b.right;
        b.right = a;
        updateBF(a);
        updateBF(b);
        return b;
    }

    private void updateBF(Node<E> node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.bf = rightHeight - leftHeight;
    }

    private int height(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<E> node) {
        if (node != null) {
            System.out.print(node + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Recorrido por niveles (BFS) recursivo
    public void bfs() {
        int h = height(root);
        for (int i = 0; i <= h; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }

    private void printLevel(Node<E> node, int level) {
        if (node == null) return;
        if (level == 0) {
            System.out.print(node + " ");
        } else {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }
// Ejercicio 3: recorrido por niveles (BFS) recursivo
    public void bfsRecursivo() {
        int h = height(root);
        for (int i = 0; i <= h; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }
    // Clase principal para probar todos los ejercicios
    public static void main(String[] args) {
        AVLTreeEliminacion<Integer> tree = new AVLTreeEliminacion<>();
        int[] datos = {50, 30, 70, 20, 40, 60, 80};
        for (int d : datos) tree.insert(d);

        System.out.println("Preorden inicial:");
        tree.preorder();

        tree.remove(70);
        System.out.println("\nDespués de eliminar 70:");
        tree.preorder();

        tree.remove(60);
        System.out.println("\nDespués de eliminar 60:");
        tree.preorder();

        System.out.println("\nRecorrido BFS recursivo:");
        tree.bfs();

        System.out.print("\nBFS recursivo: ");
        tree.bfsRecursivo();

        // Ejercicio 5: inserciones y eliminaciones con evidencia
        System.out.println("\n--- Ejercicio 5: evidencia de rotaciones ---");
        AVLTreeEliminacion<Integer> testTree = new AVLTreeEliminacion<>();
        int[] insertar = {10, 20, 5, 4, 15, 25, 30};
        for (int val : insertar) {
            testTree.insert(val);
            System.out.print("Insertado " + val + ": ");
            testTree.preorder();
        }

        int[] eliminar = {5, 4, 15};
        for (int val : eliminar) {
            testTree.remove(val);
            System.out.print("Eliminado " + val + ": ");
            testTree.preorder();
        }
    }
}